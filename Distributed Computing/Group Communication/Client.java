import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static Socket clientSocket;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket(HOST, PORT);
            System.out.println("\nConnected to the server " + HOST);

            // Create a thread to receive messages from the server
            Thread receiveThread = new Thread(new ReceiveMessages());
            receiveThread.start();

            // Send messages to the server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println();
                String message = scanner.nextLine();
                sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send messages to the server
    private static void sendMessage(String message) {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable for receiving messages from the server
    static class ReceiveMessages implements Runnable {
        public void run() {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
