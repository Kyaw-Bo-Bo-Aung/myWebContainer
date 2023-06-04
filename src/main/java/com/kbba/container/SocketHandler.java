package com.kbba.container;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

public class SocketHandler extends Thread{
    private Socket socket;
    
    private Map<String, HttpServlet> handlers;
    
    public SocketHandler(Socket s, Map<String, HttpServlet> handlers) {
        this.socket = s;
        this.handlers = handlers;
    }
    
    @Override
    public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            Request request = new Request(in);
            
            if(!request.parse()) {
                out = new PrintWriter(socket.getOutputStream());
                String responseBody = "<html><body>Current Time: " + LocalDateTime.now() + "</body></html>";

                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("Content-Length: " + responseBody.length());
                out.println();
                out.println(responseBody);
                out.flush(); 
            }
            
            System.out.println("METHODS => "+request.getMethod());
            System.out.println("PATH => "+request.getPath());
            request.getHeaders().forEach((String key, String value)-> {
               System.out.println("HEADERS ... : " + key + ":" + value); 
            });
            request.getRequestParams().forEach((String key, String value)-> {
                System.out.println("Request Params ... : " + key + ":" + value); 
            });
//            String line = in.readLine();
//            
//            while (!line.isEmpty()) {
//                System.out.println(line);
//                line = in.readLine();
//            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) in.close();
                if(out != null) out.close();
                if(socket != null) socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
