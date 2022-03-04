import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer extends Thread {
    public final static int PORT = 1234;
    private final static int BUFFER = 1024;
    private DatagramSocket socket;
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientPorts;
    private ArrayList<String> clientUsernames;
    private HashSet<String> existingClients;

    public ChatServer() throws IOException {
        socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList();
        clientPorts = new ArrayList();
        existingClients = new HashSet();
        clientUsernames = new ArrayList<>();
    }

    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte)0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                @Deprecated
                String content = new String(buf, buf.length);

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                String username;
                String id = clientAddress.toString() + "," + clientPort;
                if (!existingClients.contains(id)) {
                    username = content.substring(0, content.indexOf(" "));
                    clientUsernames.add(username);
                    existingClients.add(id);
                    clientPorts.add(clientPort);
                    clientAddresses.add(clientAddress);
                } else {
                    int i = 0;
                    for(String ec : existingClients){
                        if(ec.equals(id)){
                            break;
                        }
                        i++;
                    }
                    username = clientUsernames.get(i);
                }

                System.out.println(username + " > " + content.trim());
                byte[] data = (username + " > " +  content).getBytes();
                for (int i=0; i < clientAddresses.size(); i++) {
                    InetAddress ca = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, ca, cp);
                    socket.send(packet);
                }
            } catch(Exception e) {
                System.err.println(e);
                stopServer();
            }
        }
    }

    public void stopServer(){
        socket.close();
    }

    public static void main(String args[]) throws Exception {
        ChatServer s = new ChatServer();
        s.start();
    }
}