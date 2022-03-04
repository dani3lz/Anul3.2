import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

class MessageSender implements Runnable {
    public final static int PORT = 1234;
    private DatagramSocket sock;
    private String hostname;
    private String username;

    MessageSender(DatagramSocket s, String h, String u) {
        sock = s;
        hostname = h;
        username = u;
    }

    private void sendMessage(String s) throws Exception {
        byte buf[] = s.trim().getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }

    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage(username + " - has entered.");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                sendMessage(in.readLine());
            } catch(Exception e) {
                System.err.println(e);
                stopSender();
            }
        }
    }

    public void stopSender(){
        sock.close();
    }
}

class MessageReceiver implements Runnable {
    DatagramSocket sock;
    String username;
    byte buf[];

    MessageReceiver(DatagramSocket s, String u) {
        sock = s;
        buf = new byte[1024];
        username = u;
    }
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String userLocal = received.substring(0, received.indexOf(" "));
                if(!username.equals(userLocal)) {
                    System.out.println(received.trim());
                }
            } catch(Exception e) {
                System.err.println(e);
                stopReceiver();
            }
        }
    }

    public void stopReceiver(){
        sock.close();
    }
}

class Main{

    public static void main(String[] args) throws IOException {
        System.out.print("Username: ");
        String username = new Scanner(System.in).nextLine();
        String host = "localhost";
        DatagramSocket socket = new DatagramSocket();
        MessageReceiver r = new MessageReceiver(socket, username);
        MessageSender s = new MessageSender(socket, host, username);
        Thread rt = new Thread(r);
        Thread st = new Thread(s);
        rt.start(); st.start();
    }
}