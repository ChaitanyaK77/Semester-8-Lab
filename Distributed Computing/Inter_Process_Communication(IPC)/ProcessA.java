import java.net.*;

public class ProcessA {
    public static void main(String[] args) {
        try {
            // Port for Process A (listening port)
            int listenPort = 9876;
            // Ports for communication with Process B and Process C
            int portB = 9877;
            int portC = 9878;

            // Create a DatagramSocket for Process A to listen on
            DatagramSocket socket = new DatagramSocket(listenPort);

            // Thread to listen for incoming messages
            Thread listenerThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket); // Blocking call, waiting for message
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Process A received: " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            listenerThread.start();

            // Send messages to Process B and Process C
            DatagramSocket sendSocket = new DatagramSocket();
            String messageToB = "Hello from A to B";
            String messageToC = "Hello from A to C";

            // Send message to Process B
            DatagramPacket sendPacketB = new DatagramPacket(messageToB.getBytes(), messageToB.length(),
                    InetAddress.getByName("localhost"), portB);
            sendSocket.send(sendPacketB);

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
