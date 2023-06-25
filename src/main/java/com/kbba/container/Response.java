package com.kbba.container;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {
    private OutputStream outputStream;
    private PrintWriter printWriter;
    
    
    public Response(OutputStream output) {
        this.outputStream  = output;
        this.printWriter = new PrintWriter(outputStream);
    }


    public OutputStream getOutputStream() {
        return outputStream;
    }


    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
