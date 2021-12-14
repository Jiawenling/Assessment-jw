package com.jw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPClientConnection implements Runnable {

    public static void checkDirExists(String dir){
        
    }

    private final Socket socket;
    private int id;
    private String inputFile;


    public HTTPClientConnection(Socket socket, int id, String inputFile) {
        this.socket = socket;
        this.id = id;
        this.inputFile = inputFile;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";
        System.out.println("Connection ID: " + id);
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

            System.out.println("Client " + id + ": " + line);
            
            try {
                
                if (line.startsWith("GET")) {
                    // System.out.println("Sending a cookie to client " + id);
                    // out.println("cookie-text " + 
                    //         new Cookie().getCookie(inputFile));
                    // out.flush();
                    // line = in.readLine();
                } else {
                    out.println("Server: you said " + line);
                    out.flush();
                    line = in.readLine();
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
        }
    }
    
}
