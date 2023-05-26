package com.kbba.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class SimpleWebContainer {
    
    public final int port;
    
    public SimpleWebContainer(int p) {
        this.port = p;
    }
    
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        
        while(true) {
            try(Socket socket = serverSocket.accept();) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = in.readLine();
                
                while (!line.isEmpty()) {
                    System.out.println(line);
                    line = in.readLine();
                }
                
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String responseBody = "<html><body>Current Time: " + LocalDateTime.now() + "</body></html>";

                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("Content-Length: " + responseBody.length());
                out.println();
                out.println(responseBody);
                out.flush();   
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        SimpleWebContainer container = new SimpleWebContainer(8888);
        container.start();
    }
}
