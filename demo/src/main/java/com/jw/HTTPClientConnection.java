package com.jw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
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
            String command = userInput[0];
            String userFile = userInput[1];
            
            
            try {
                
                if (command.startsWith("GET")) {


                    File file = new File("static"+userFile);

                    if (!file.exists()){
                        
                        String response = "HTTP/1.1 404 Not Found\r\n\r\n"+userFile+ " not found\r\n";
                        socket.getOutputStream().write(response.getBytes("UTF-8"));
                        socket.close();

                    } else{

                        if(userFile.contains(".png")){

                            String response = "HTTP/1.1 200 OK\r\nContent-Type:image/png\r\n\r\n";
                            socket.getOutputStream().write(response.getBytes("UTF-8"));
                            byte[] bytes = Files.readAllBytes(file.toPath());
                            socket.getOutputStream().write(bytes);
                            socket.close();

                        } else{
                            System.out.println(file.getAbsolutePath());
                            String response = "HTTP/1.1 200 OK\r\n\r\n";
                            socket.getOutputStream().write(response.getBytes("UTF-8"));
                            byte[] bytes = Files.readAllBytes(file.toPath());
                            socket.getOutputStream().write(bytes);
                            socket.close();
                        }
                        
                    }
                   
                } else {
                    out.println("HTTP/1.1 405 Method Not Allowed\r\n\r\n"+command+ "not supported\r\n");
                    out.flush();
                    
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
        }
    }
    
}
