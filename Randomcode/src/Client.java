import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) {

        try {
            Socket s = new Socket("127.0.0.1", 5001);

            InputStream i=s.getInputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(i));
            PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String receivedMessage;
            String sendMessage;

            while (true) {
                // Sending message to server
                System.out.print("Client: ");
                sendMessage = consoleReader.readLine();
                writer.println(sendMessage);

                // Check if client wants to end the connection
                if (sendMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                // Reading message from server
                receivedMessage = reader.readLine();
                System.out.println("Server: " + receivedMessage);
            }

            // Close all resources


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
