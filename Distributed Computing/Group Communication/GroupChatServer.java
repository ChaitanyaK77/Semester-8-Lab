import java.io.*;
import java.net.*;
import java.util.*;

public class GroupChatServer {
    static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server running...");
        while (true) {
            Socket socket = server.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            clients.add(out);
            new Thread(() -> handleClient(socket, out)).start();
        }
    }
    static void handleClient(Socket socket, PrintWriter out) {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            out.println("Enter your name:");
            String name = in.nextLine();
            broadcast(name + " joined the chat");
            while (in.hasNextLine())
                broadcast(name + ": " + in.nextLine());
        } catch (Exception e) {}
    }

    static void broadcast(String msg) {
        for (PrintWriter client : clients)
            client.println(msg);
    }
} 
