package com.kbba.container;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimpleWebContainer {
    
    public final int port;
    public final String configFile;
    public Map<String, HttpServlet> handlers = new HashMap<>();
    
    public SimpleWebContainer(int p, String configFile) {
        this.port = p;;
        this.configFile = configFile;
    }
    
    @SuppressWarnings("resource")
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                Thread socketHandler = new SocketHandler(socket, handlers);
                socketHandler.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void loadPropertiesFile() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream(configFile);
        if(input == null)
            throw new FileNotFoundException("file cannot be load.");
        
        Properties properties = new Properties();
        properties.load(input);
        properties.forEach((key, value) -> {
            HttpServlet servlet = getServlet((String)value);
            servlet.init();
            handlers.put((String) key, servlet);
        });
    }
    
    private HttpServlet getServlet(String className) {
        try {
            return (HttpServlet) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        SimpleWebContainer container = new SimpleWebContainer(8888, "config.properties");
        container.loadPropertiesFile();
        container.handlers.forEach((url, httpServlet) -> {
            System.out.println(url);
            httpServlet.doGet();
        });
        container.start();
    }
}
