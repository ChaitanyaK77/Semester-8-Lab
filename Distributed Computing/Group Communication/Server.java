import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final String HOST = "localhost"; // Use "localhost" or "0.0.0.0"
    private static final int PORT = 8080; // Change port to something else if needed
    private static ServerSocket serverSocket;
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(HOST));
            System.out.println("\nServer started on " + HOST + ":" + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("\nClient " + clientSocket.getInetAddress() + " connected..");

                // Create a new thread to handle the client
                Thread clientThread = new Thread(new HandleClient(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable to handle each client
    static class HandleClient implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;
        private BufferedReader reader;

        public HandleClient(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                writer = new PrintWriter(outputStream, true);

                synchronized (clientWriters) {
                    clientWriters.add(writer);
                }

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("\nMessage from Client " + clientSocket.getInetAddress() + ": " + message);
                    sendToAllClients("Client " + clientSocket.getInetAddress() + ": " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    synchronized (clientWriters) {
                        clientWriters.remove(writer);
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendToAllClients(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
