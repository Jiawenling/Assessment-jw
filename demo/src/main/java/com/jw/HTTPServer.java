package com.jw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {

    public static void main(Integer port, List<String> filepath) {
                
        try (
            ServerSocket serverSocket = new ServerSocket(port);
            
            
        ){
            
            ExecutorService threadPool = Executors.newFixedThreadPool(3);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client connected: " + socket.getLocalAddress().getHostAddress());
                HTTPClientConnection worker = new HTTPClientConnection(socket, filepath);
                threadPool.submit(worker);
            }

        } catch(IOException e){
            e.printStackTrace();
        } 

        
        
    }
}
