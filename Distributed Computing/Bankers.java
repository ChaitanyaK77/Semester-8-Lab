import java.util.Scanner;

public class BankersAlgorithm {
    private int processes; // Number of processes
    private int resources; // Number of resource types
    private int[] available; // Available resources
    private int[][] max; // Maximum demand
    private int[][] allocation; // Current allocation
    private int[][] need; // Needed resources

    private Scanner input = new Scanner(System.in);

    // Initialize system
    public void init() {
        // Get basic info
        System.out.print("Enter number of processes: ");
        processes = input.nextInt();
        System.out.print("Enter number of resources: ");
        resources = input.nextInt();

        // Create arrays
        available = new int[resources];
        max = new int[processes][resources];
        allocation = new int[processes][resources];
        need = new int[processes][resources];

        // Get available resources
        System.out.println("Enter available resources:");
        for (int i = 0; i < resources; i++) {
            System.out.print("Resource R" + i + ": ");
            available[i] = input.nextInt();
        }

        // Get maximum resources needed
        System.out.println("Enter maximum resources for each process:");
        for (int i = 0; i < processes; i++) {
            System.out.print("Process P" + i + ": ");
            for (int j = 0; j < resources; j++) {
                max[i][j] = input.nextInt();
            }
        }

        // Get allocated resources
        System.out.println("Enter allocated resources for each process:");
        for (int i = 0; i < processes; i++) {
            System.out.print("Process P" + i + ": ");
            for (int j = 0; j < resources; j++) {
                allocation[i][j] = input.nextInt();
                // Subtract from available
                available[j] -= allocation[i][j];
            }
        }

        // Calculate need matrix (max - allocation)
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    // Display current system state
    public void display() {
        // Show need matrix
        System.out.println("\nNeed Matrix:");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < resources; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }

        // Show available resources
        System.out.print("Available: ");
        for (int i = 0; i < resources; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();
    }

    // Check if system is in safe state
    public boolean isSafe() {
        boolean[] finished = new boolean[processes];
        int[] work = available.clone();
        int[] safeSeq = new int[processes];
        int count = 0;

        // Find a process that can finish
        while (count < processes) {
            boolean found = false;

            for (int i = 0; i < processes; i++) {
                if (!finished[i]) {
                    // Check if all needed resources are available
                    boolean canFinish = true;
                    for (int j = 0; j < resources; j++) {
                        if (need[i][j] > work[j]) {
                            canFinish = false;
                            break;
                        }
                    }

                    if (canFinish) {
                        // Process can finish, add its resources back
                        for (int j = 0; j < resources; j++) {
                            work[j] += allocation[i][j];
                        }

                        // Mark as finished and add to safe sequence
                        finished[i] = true;
                        safeSeq[count] = i;
                        count++;
                        found = true;
                    }
                }
            }

            // If no process could finish, system is unsafe
            if (!found) {
                System.out.println("System is UNSAFE!");
                return false;
            }
        }

        // System is safe
        System.out.print("System is SAFE! Safe sequence: ");
        for (int i = 0; i < processes; i++) {
            System.out.print("P" + safeSeq[i]);
            if (i < processes - 1)
                System.out.print(" -> ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        BankersAlgorithm banker = new BankersAlgorithm();

        System.out.println("BANKER'S ALGORITHM");
        System.out.println("==================");

        // Setup system
        banker.init();
        banker.display();
        banker.isSafe();
    }
}
