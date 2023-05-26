package com.kbba.container;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebContainer {
    
    public final int port;
    
    public SimpleWebContainer(int p) {
        this.port = p;
    }
    
    @SuppressWarnings("resource")
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                Thread socketHandler = new SocketHandler(socket);
                socketHandler.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        SimpleWebContainer container = new SimpleWebContainer(8888);
        container.start();
    }
}
