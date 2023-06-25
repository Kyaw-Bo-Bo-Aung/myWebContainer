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
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            Request request = new Request(in);
            
            if(!request.parse()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String responseBody = "<html><body>Cannot process your request</body></html>";

                out.println("HTTP/1.1 500 Internal Server Error");
                out.println("Content-Type: text/html");
                out.println("Content-Length: " + responseBody.length());
                out.println();
                out.println(responseBody);
                out.flush(); 
            } else {
                HttpServlet servlet = handlers.get(request.getPath());
                if(servlet == null) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    String responseBody = "<html><body>Cannot process your request</body></html>";
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Content-Type: text/html");
//                    out.println("Content-Length: " + responseBody.length());
                    out.println();
                    out.println(responseBody);
                    out.flush();
                } else {
                    Response response = new Response(socket.getOutputStream());
                    PrintWriter out = response.getPrintWriter();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Content-Length: " +  100000);
                    out.println();
                    servlet.service(request, response);
                    out.flush();
                }
            }
            
                      
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null) socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
