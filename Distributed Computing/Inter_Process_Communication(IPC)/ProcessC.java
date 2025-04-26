import java.net.*;

public class ProcessC {
    public static void main(String[] args) {
        try {
            // Port for Process C (listening port)
            int listenPort = 9878;
            // Ports for communication with Process A and Process B
            int portA = 9876;
            int portB = 9877;

            // Create a DatagramSocket for Process C to listen on
            DatagramSocket socket = new DatagramSocket(listenPort);

            // Thread to listen for incoming messages
            Thread listenerThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket); // Blocking call, waiting for message
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Process C received: " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            listenerThread.start();

            // Send messages to Process A and Process B
            DatagramSocket sendSocket = new DatagramSocket();
            String messageToA = "Hello from C to A";
            String messageToB = "Hello from C to B";

            // Send message to Process A
            DatagramPacket sendPacketA = new DatagramPacket(messageToA.getBytes(), messageToA.length(),
                    InetAddress.getByName("localhost"), portA);
            sendSocket.send(sendPacketA);

            // Send message to Process B
            DatagramPacket sendPacketB = new DatagramPacket(messageToB.getBytes(), messageToB.length(),
                    InetAddress.getByName("localhost"), portB);
            sendSocket.send(sendPacketB);

            // Close the socket
            sendSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
