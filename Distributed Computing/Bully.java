import java.util.*;

public class Bully {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of machines
        System.out.print("Enter no. of machines: ");
        int noOfMachines = scanner.nextInt();

        // Read the machine that sends the message
        System.out.print("Enter machine who sends a message: ");
        int detectMachine = scanner.nextInt();

        // Read the machine that does not respond (the "dead" machine)
        System.out
                .print("Enter machine who does not respond to Machine " + detectMachine + " within time interval T: ");
        int downMachine = scanner.nextInt();

        // List to hold machines that will receive the election message
        List<Integer> highPriorityMachines = new ArrayList<>();

        // Add high priority machines excluding the down machine
        for (int i = detectMachine + 1; i <= noOfMachines; i++) {
            // if (i != downMachine) {
            highPriorityMachines.add(i);
            // }
        }

        System.out.println();

        // Election process starts
        System.out.print("Machine " + detectMachine + " sending Election Message to Machines ---->");
        for (int machine : highPriorityMachines) {
            System.out.print(" " + machine);
        }

        System.out.println("\n");

        // Responding machines
        int c = 0;
        for (int machine : highPriorityMachines) {
            // Simulating machine dead (downMachine does not respond)
            if (machine == downMachine) {
                System.out.println("Machine " + machine + " is down and not responding.");
            } else {
                c++;
                System.out.println("Machine " + machine + " responding OK to the Election Message");
            }
        }

        System.out.println("\nNo of machines responded OK to Election Message: " + c);

        // Main election loop
        while (highPriorityMachines.size() > 1) {
            c = 0;
            int sender = highPriorityMachines.get(0);
            int size = highPriorityMachines.size();

            if (sender == downMachine) {
                highPriorityMachines.remove(0);
                continue;
            }

            System.out.print("\nMachine " + sender + " sending Election Message to Machines ---->");
            for (int i = 1; i < size; i++) {
                System.out.print(" " + highPriorityMachines.get(i));
            }

            System.out.println("\n");

            boolean flag = true;
            // Responding machines
            for (int i = 1; i < size; i++) {
                // Simulating machine dead (downMachine does not respond)
                if (highPriorityMachines.get(i) == downMachine) {
                    System.out.println("Machine " + highPriorityMachines.get(i) + " is down and not responding.");
                    if (highPriorityMachines.size() == 2)
                        flag = false;
                } else {
                    c++;
                    System.out.println(
                            "Machine " + highPriorityMachines.get(i) + " responding OK to the Election Message");
                }
            }

            System.out.println("\nNo of machines responded OK to Election Message: " + c);

            // Remove the first machine as it's now out of the election
            if (!flag)
                break;
            highPriorityMachines.remove(0);
        }

        // Output the elected coordinator
        System.out.println("\nMachine " + highPriorityMachines.get(0) + " elected as the New Coordinator\n");

        // Notify other machines
        System.out.print("Machine " + highPriorityMachines.get(0)
                + " sending message I am the New Coordinator to Machines ---->");
        for (int i = 1; i < noOfMachines; i++) {
            if (i == downMachine || i == highPriorityMachines.get(0)) {
                continue;
            }
            System.out.print(" " + i);
        }

        scanner.close();
    }
}
