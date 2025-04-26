import java.io.*;
import java.net.*;
import java.util.*;

public class GroupChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        Scanner serverIn = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner userIn = new Scanner(System.in);

        new Thread(() -> {
            while (serverIn.hasNextLine())
                System.out.println(serverIn.nextLine());
        }).start();

        while (userIn.hasNextLine())
            out.println(userIn.nextLine());
    }
}
