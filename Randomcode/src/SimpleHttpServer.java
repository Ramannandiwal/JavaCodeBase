// Java Program to Set up a Basic HTTP Server
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

// Driver Class
public class SimpleHttpServer
{
    // Main Method
    public static void main(String[] args) throws IOException
    {
        // Create an HttpServer instance
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create a context for a specific path and set the handler
        server.createContext("/", new MyHandler("hello raman"));

        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();

        System.out.println("Server is running on port 8000");
    }

    // define a custom HttpHandler
    static class MyHandler implements HttpHandler {
        String response;
        MyHandler(String s){
            this.response=s;
        }
        @Override
        public void handle(HttpExchange exchange) throws IOException
        {
            // handle the request

            exchange.sendResponseHeaders(200, this.response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(this.response.getBytes());
            os.close();
        }
    }
}
