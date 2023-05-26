package com.kbba.container;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class SocketHandler extends Thread{
    private Socket socket;
    
    public SocketHandler(Socket s) {
        socket = s;
    }
    
    @Override
    public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();
            
            while (!line.isEmpty()) {
                System.out.println(line);
                line = in.readLine();
            }
            
            out = new PrintWriter(socket.getOutputStream());
            String responseBody = "<html><body>Current Time: " + LocalDateTime.now() + "</body></html>";

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("Content-Length: " + responseBody.length());
            out.println();
            out.println(responseBody);
            out.flush(); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
