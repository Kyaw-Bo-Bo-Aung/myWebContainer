package com.kbba.servlet;

import com.kbba.container.HttpServlet;

public class SignUpServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("SignUpServlet init() called .....");
    }
    
    @Override
    public void doGet() {
        System.out.println("sign up");
    }

}
