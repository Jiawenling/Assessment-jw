package com.jw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class HTTPClientConnection implements Runnable {

    public static void checkDirExists(String dir){
        
    }

    private final Socket socket;
    private final List<String> filepath;
   


    public HTTPClientConnection(Socket socket, List<String> filepath) {
        this.socket = socket;
        this.filepath = filepath;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";
        // System.out.println("Connection ID: " + id);
        boolean forcedClose = false;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            line = in.readLine();
           

            
        } catch (IOException ioe) {
            System.out.println("Something went wrong..");
        }

        while (!forcedClose && null != line) {

            String[] userInput = line.split("\\s");
            String userFile = userInput[2];
            
            try {
                
                if (line.startsWith("GET")) {

                    Date today = new Date();
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                    socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                    

                    // System.out.println("Sending a cookie to client " + id);
                    // out.println("cookie-text " + 
                    //         new Cookie().getCookie(inputFile));
                    // out.flush();
                    // line = in.readLine();
                } else {
                    out.println("HTTP/1.1 405 Method Not Allowed\r\n"+line+ "not supported\r\n");
                    out.flush();
                    
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
        }
    }
    
}
