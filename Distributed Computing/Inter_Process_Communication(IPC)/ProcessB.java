import java.net.*;

public class ProcessB {
    public static void main(String[] args) {
        try {
            // Port for Process B (listening port)
            int listenPort = 9877;
            // Ports for communication with Process A and Process C
            int portA = 9876;
            int portC = 9878;

            // Create a DatagramSocket for Process B to listen on
            DatagramSocket socket = new DatagramSocket(listenPort);

            // Thread to listen for incoming messages
            Thread listenerThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket); // Blocking call, waiting for message
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Process B received: " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            listenerThread.start();

            // Send messages to Process A and Process C
            DatagramSocket sendSocket = new DatagramSocket();
            String messageToA = "Hello from B to A";
            String messageToC = "Hello from B to C";

            // Send message to Process A
            DatagramPacket sendPacketA = new DatagramPacket(messageToA.getBytes(), messageToA.length(),
                    InetAddress.getByName("localhost"), portA);
            sendSocket.send(sendPacketA);

            // Send message to Process C
            DatagramPacket sendPacketC = new DatagramPacket(messageToC.getBytes(), messageToC.length(),
                    InetAddress.getByName("localhost"), portC);
            sendSocket.send(sendPacketC);

            // Close the socket
            sendSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
