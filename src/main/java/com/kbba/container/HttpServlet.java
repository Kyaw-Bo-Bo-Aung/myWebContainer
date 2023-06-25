package com.kbba.container;

public abstract class HttpServlet {
    public void init() {
        System.out.println("httpservlet init ......");
    }
    
    public void service(Request request, Response response) {
        if("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        }
    }
    
    public void doGet(Request request, Response response) {
        System.out.println("httpservlet default doGet() ......");
    }
    
    public void doPost(Request request, Response response) {
        System.out.println("httpservlet default doPost() ......");
    }
}
