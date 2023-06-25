package com.kbba.servlet;

import java.io.PrintWriter;

import com.kbba.container.HttpServlet;
import com.kbba.container.Request;
import com.kbba.container.Response;

public class SignUpServlet extends HttpServlet {    
    @Override
    public void init() {
        System.out.println("SignUpServlet init() called .....");
    }
    
    @Override
    public void doGet(Request request, Response response) {
        PrintWriter out = new PrintWriter(response.getOutputStream());
        System.out.println("yohoo");
        out.println("<html><body>");
        out.println("<form method=\"POST\" action=\"/signup\">");
        out.println("First Name : <input type=\"text\" id=\"fname\" name=\"fname\" value=\"John\"><br>");
        out.println("Last Name : <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Doe\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body></html>"); 
    }

    @Override
    public void doPost(Request request, Response response) {
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println("<html><body>");
        out.println("<p>You are calling the doPost() of signup</p>");        
        out.println("</body></html>");
    }
}
