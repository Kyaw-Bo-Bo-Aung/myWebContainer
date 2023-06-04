package com.kbba.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private BufferedReader in;
    private String method;
    private String path;
    private Map<String, String>headers = new HashMap<>();
    private Map<String, String> requestParams = new HashMap<>();
    
    public Request(BufferedReader in) {
        this.in = in;
    }

    public BufferedReader getIn() {
        return in;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }
    
    private void parseRequestParams(String queryString) {
        for(String pair: queryString.split("&")) {
            String[] pairArray = pair.split("=");
            requestParams.put(pairArray[0], pairArray[1]);
        }
    }
    
    
    public boolean parse() throws IOException {
        String line = in.readLine();
        String[] firstLineArray = line.split(" ");
        if(firstLineArray.length != 3) return false;
        
        method = firstLineArray[0];
        
        String url = firstLineArray[1];
        int queryStringIndex = url.indexOf("?");
        if(queryStringIndex > -1) {
            path = url.substring(0, queryStringIndex);
            parseRequestParams(url.substring(queryStringIndex + 1));
        } else {
            path = url;
        }
        
        while(!line.isEmpty()) {
            line = in.readLine();
//            System.out.println(line);
            if(!"".equals(line)) {
                String[] headerPair = line.split(":");
                headers.put(headerPair[0], headerPair[1]);
            }
        }
        
        return true;
    }
    
}
