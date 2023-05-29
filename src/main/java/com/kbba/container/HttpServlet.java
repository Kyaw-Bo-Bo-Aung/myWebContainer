package com.kbba.container;

public abstract class HttpServlet {
    public void init() {
        System.out.println("httpservlet init ......");
    }
    
    public void service() {
        // TODO - request and response object to create
    }
    
    public void doGet() {
        System.out.println("httpservlet doget ......");
    }
    
    public void doPost() {
        System.out.println("httpservlet dopost ......");
    }
}
