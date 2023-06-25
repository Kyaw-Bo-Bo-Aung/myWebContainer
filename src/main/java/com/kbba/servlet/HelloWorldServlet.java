package com.kbba.servlet;

import java.io.PrintWriter;

import com.kbba.container.HttpServlet;
import com.kbba.container.Request;
import com.kbba.container.Response;

public class HelloWorldServlet extends HttpServlet{

    @Override
    public void init() {
        System.out.println("HelloWorldServlet init() called ****");
    }
    
    @Override
    public void doGet(Request request, Response response) {
        System.out.println("yep");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println("<html><body>");
        out.println("<p>You are calling the doPost() of signup</p>");        
        out.println("</body></html>");
    }
    
    @Override
    public void doPost(Request request, Response response) {
        
    }
}
