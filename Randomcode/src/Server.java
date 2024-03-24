import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
public class Server {
    public static void main(String[] javaargs) {
        try {
            JFrame frame;
            frame = new JFrame("first way");
            JPanel p = new JPanel();
// creates instance of JButton
            JButton button = new JButton("let's see");
            button.setBounds(200, 150, 90, 50);
// setting close operation
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// adds button in JFrame
            frame.add(button);
// sets 500 width and 600 height
            frame.setSize(500, 600);
// uses no layout managers
            frame.setLayout(null);
// makes the frame visible
            frame.setVisible(true);
            ServerSocket serverSocket = new ServerSocket(5001);
            System.out.println("Server started. Waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

                    /* InputStream i= clientSocket.getInputStream();

			  InputStreamReader t=new InputStreamReader(i);

			  BufferedReader reader = new BufferedReader(t);*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


			  /*OutputStream o=clientSocket.getOutputStream();

			  PrintWriter writer=new PrintWriter(o);*/

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            InputStreamReader y=new InputStreamReader(System.in);
            BufferedReader consoleReader = new BufferedReader(y);

            /*  BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));*/

            String receivedMessage;
            String sendMessage;


            while (true) {
                // Reading message from client
                receivedMessage = reader.readLine();
                System.out.println("Client: " + receivedMessage);


                // Check if client wants to end the connection
                if (receivedMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                // Sending message to client
                System.out.print("Server: ");
                sendMessage = consoleReader.readLine();
                writer.println(sendMessage);
            }

            // Close all resources

            System.out.println("Server closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {

        }
    }
}
