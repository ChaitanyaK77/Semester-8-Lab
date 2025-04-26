import java.util.*;

public class LoadBalancingSimulation {
    // Map to store node IDs and their corresponding loads
    private Map<Integer, Integer> nodeLoads;

    // Constructor to initialize the system
    public LoadBalancingSimulation() {
        nodeLoads = new HashMap<>();
        // Initialize with some nodes
        nodeLoads.put(0, 4);
        nodeLoads.put(1, 3);
        nodeLoads.put(2, 3);
    }

    // Add a process by assigning it to the least loaded node
    public void addProcess() {
        if (nodeLoads.isEmpty()) {
            System.out.println("No nodes available. Please add a node first.");
            return;
        }

        // Find the node with the minimum load
        int minLoadNode = findMinLoadNode();

        // Increment the load of the selected node
        nodeLoads.put(minLoadNode, nodeLoads.get(minLoadNode) + 1);

        System.out.println("Process added to Node " + minLoadNode);
    }

    // Find the node with the minimum load
    private int findMinLoadNode() {
        int minNode = -1;
        int minLoad = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : nodeLoads.entrySet()) {
            if (entry.getValue() < minLoad) {
                minLoad = entry.getValue();
                minNode = entry.getKey();
            }
        }

        return minNode;
    }

    // Remove a process from a specified node
    public void removeProcess() {
        if (nodeLoads.isEmpty()) {
            System.out.println("No nodes available.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter node ID to remove a process from: ");
        int nodeId = scanner.nextInt();

        if (!nodeLoads.containsKey(nodeId)) {
            System.out.println("Node " + nodeId + " does not exist.");
            return;
        }

        int currentLoad = nodeLoads.get(nodeId);

        if (currentLoad <= 0) {
            System.out.println("Node " + nodeId + " has no processes to remove.");
            return;
        }

        // Decrement the load of the specified node
        nodeLoads.put(nodeId, currentLoad - 1);

        System.out.println("Process removed from Node " + nodeId);
    }

    // Add a new node to the system
    public void addNode() {
        int newNodeId = 0;

        // Find the next available node ID
        while (nodeLoads.containsKey(newNodeId)) {
            newNodeId++;
        }

        // Add the new node with zero load
        nodeLoads.put(newNodeId, 0);

        System.out.println("Node " + newNodeId + " added with load 0");
    }

    // Remove a node and redistribute its processes
    public void removeNode() {
        if (nodeLoads.isEmpty()) {
            System.out.println("No nodes available to remove.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter node ID to remove: ");
        int nodeId = scanner.nextInt();

        if (!nodeLoads.containsKey(nodeId)) {
            System.out.println("Node " + nodeId + " does not exist.");
            return;
        }

        // Get the load of the node to be removed
        int nodeLoad = nodeLoads.get(nodeId);

        // Remove the node
        nodeLoads.remove(nodeId);

        if (nodeLoads.isEmpty()) {
            System.out.println(
                    "Node " + nodeId + " removed. No nodes left to redistribute its " + nodeLoad + " processes.");
            return;
        }

        // Redistribute the processes to other nodes
        redistributeProcesses(nodeLoad);

        System.out.println("Node " + nodeId + " removed and its processes redistributed.");
    }

    // Redistribute processes from a removed node
    private void redistributeProcesses(int processCount) {
        for (int i = 0; i < processCount; i++) {
            int minLoadNode = findMinLoadNode();
            nodeLoads.put(minLoadNode, nodeLoads.get(minLoadNode) + 1);
        }
    }

    // Display the current system state
    public void displaySystemState() {
        System.out.println("Current Load Distribution: " + nodeLoads);
    }

    // Main method to run the simulation
    public static void main(String[] args) {
        LoadBalancingSimulation simulator = new LoadBalancingSimulation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Load Balancing Simulation Started");
        simulator.displaySystemState();

        do {
            System.out.println("\nOptions:");
            System.out.println("1. Add a process");
            System.out.println("2. Remove a process");
            System.out.println("3. Add a node");
            System.out.println("4. Remove a node");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    simulator.addProcess();
                    break;
                case 2:
                    simulator.removeProcess();
                    break;
                case 3:
                    simulator.addNode();
                    break;
                case 4:
                    simulator.removeNode();
                    break;
                case 5:
                    System.out.println("Exiting simulation...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            simulator.displaySystemState();

        } while (choice != 5);

        scanner.close();
    }
}
