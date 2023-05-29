package com.kbba.servlet;

import com.kbba.container.HttpServlet;

public class HelloWorldServlet extends HttpServlet{

    @Override
    public void init() {
        System.out.println("HelloWorldServlet init() called ****");
    }
    
    @Override
    public void doGet() {
        System.out.println("Hellworld");
    }
    
}
