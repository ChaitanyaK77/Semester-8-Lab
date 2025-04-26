import java.util.*;

public class RaymondTreeAlgorithm {
    static class Node {
        int id;
        boolean hasToken;
        int parent;
        Queue<Integer> requestQueue;

        public Node(int id, int parent, boolean hasToken) {
            this.id = id;
            this.parent = parent;
            this.hasToken = hasToken;
            this.requestQueue = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "Node " + id;
        }
    }

    private Node[] nodes;
    private int[][] adjacencyMatrix;
    private int tokenHolder;

    public RaymondTreeAlgorithm(int numNodes, int[][] adjMatrix, int rootNode) {
        nodes = new Node[numNodes];
        adjacencyMatrix = adjMatrix;
        tokenHolder = rootNode;

        // Initialize nodes
        for (int i = 0; i < numNodes; i++) {
            int parent = findParent(i);
            boolean hasToken = (i == rootNode);
            nodes[i] = new Node(i, parent, hasToken);
        }
    }

    private int findParent(int nodeId) {
        if (nodeId == tokenHolder)
            return -1; // Root has no parent

        // In the adjacency matrix, a value of 1 indicates a connection
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][nodeId] == 1) {
                return i; // This is the parent
            }
        }
        return -1; // No parent found
    }

    public void requestCriticalSection(int requesterId) {
        System.out.println("Process " + requesterId + " wants to enter the CS");

        if (nodes[requesterId].hasToken) {
            enterCriticalSection(requesterId);
            return;
        }

        // Add request to own queue
        nodes[requesterId].requestQueue.add(requesterId);

        // Send request to parent
        int parent = nodes[requesterId].parent;
        System.out.println("Process " + requesterId + " sending Request to parent " + parent);
        printRequestQueue(requesterId);

        // Forward request up the tree
        forwardRequest(parent, requesterId);
    }

    private void forwardRequest(int currentNode, int originalRequester) {
        // If current node has the token
        if (nodes[currentNode].hasToken) {
            System.out.println("Parent process " + currentNode
                    + " has the token and sends the token to the request process " + originalRequester);

            // Pass token to requester
            nodes[currentNode].hasToken = false;
            nodes[originalRequester].hasToken = true;
            tokenHolder = originalRequester;

            printRequestQueue(currentNode);

            // Requester can now enter CS
            enterCriticalSection(originalRequester);
        } else {
            // Add to queue if not already there
            if (!nodes[currentNode].requestQueue.contains(originalRequester)) {
                nodes[currentNode].requestQueue.add(originalRequester);
            }

            // Continue forwarding up the tree if not root
            if (nodes[currentNode].parent != -1) {
                System.out.println("Process " + currentNode + " forwarding request from " + originalRequester
                        + " to parent " + nodes[currentNode].parent);
                forwardRequest(nodes[currentNode].parent, originalRequester);
            }
        }
    }

    private void enterCriticalSection(int nodeId) {
        // Remove own request from queue
        if (!nodes[nodeId].requestQueue.isEmpty() && nodes[nodeId].requestQueue.peek() == nodeId) {
            nodes[nodeId].requestQueue.poll();
        }

        System.out.println("Process " + nodeId + " enters the Critical Section");
        printRequestQueue(nodeId);

        // Simulate being in critical section
        try {
            Thread.sleep(1000); // Sleep for 1 second to simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        releaseCriticalSection(nodeId);
    }

    private void releaseCriticalSection(int nodeId) {
        System.out.println("Process " + nodeId + " releases the Critical Section");

        // Check if there are pending requests
        if (nodes[nodeId].requestQueue.isEmpty()) {
            System.out.println("Request queue of process " + nodeId + " is empty. Token holder: " + nodeId);
        } else {
            // Send token to next requester
            int nextRequester = nodes[nodeId].requestQueue.poll();
            System.out.println("Process " + nodeId + " sends token to next requester: " + nextRequester);

            nodes[nodeId].hasToken = false;
            nodes[nextRequester].hasToken = true;
            tokenHolder = nextRequester;

            printRequestQueue(nodeId);
            enterCriticalSection(nextRequester);
        }
    }

    private void printRequestQueue(int nodeId) {
        System.out.print("Request Queue for Process " + nodeId + ": ");
        if (nodes[nodeId].requestQueue.isEmpty()) {
            System.out.println("Empty");
        } else {
            System.out.println(nodes[nodeId].requestQueue);
        }
    }

    public static void main(String[] args) {
        // Define a simple tree structure with adjacency matrix
        // In this matrix, row i and column j being 1 means node i is parent of node j
        int[][] adjacencyMatrix = {
                { 0, 1, 1, 0, 0 }, // Node 0 is parent of nodes 1 and 2
                { 0, 0, 0, 1, 0 }, // Node 1 is parent of node 3
                { 0, 0, 0, 0, 1 }, // Node 2 is parent of node 4
                { 0, 0, 0, 0, 0 }, // Node 3 has no children
                { 0, 0, 0, 0, 0 } // Node 4 has no children
        };

        // Initialize with 5 nodes, adjacency matrix, and node 0 as root (token holder)
        RaymondTreeAlgorithm algorithm = new RaymondTreeAlgorithm(5, adjacencyMatrix, 0);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTree Structure: Node 0 -> (Node 1 -> Node 3, Node 2 -> Node 4)");
            System.out.println("Current token holder: " + algorithm.tokenHolder);
            System.out.println("Enter the process number (0-4) who wants to enter the CS (or -1 to exit): ");

            int requester = scanner.nextInt();
            if (requester == -1)
                break;

            if (requester >= 0 && requester < algorithm.nodes.length) {
                algorithm.requestCriticalSection(requester);
            } else {
                System.out.println("Invalid process number. Please enter a number between 0 and 4.");
            }
        }

        scanner.close();
    }
}
