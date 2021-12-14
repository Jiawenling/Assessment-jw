package com.jw;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        
        //get user input on port and directory
        String directory = "";
        List<String> listOfDirectories = new ArrayList<>();
        Integer port = 3000;
        if(args!= null && args.length >0){

            List<String> arguments = Arrays.asList(args); 

            if(arguments.contains("--port")){
                Integer indexOfPort = arguments.indexOf("--port") + 1;
                port = Integer.parseInt(arguments.get(indexOfPort));
            }
            if(arguments.contains("--docRoot")){
                Integer indexOfDirectory = arguments.indexOf("--docRoot") + 1;
                directory = arguments.get(indexOfDirectory);
                if(directory.contains(":")){
                    listOfDirectories = Arrays.asList(directory.split(":"));
                    
                } 
            } else {
                listOfDirectories.add("static");
            }
                
        }

        for (String path: listOfDirectories){
            File dir = new File(path);
            if(!dir.exists()){
                System.out.println("Directory does not exist in this path");
                System.exit(1);
            } 

            if(!dir.isDirectory()){
                System.out.println("This path is not a directory");
                System.exit(1);
            }

            if(!Files.isReadable(dir.toPath())){
                System.out.println("File not readable by server");
                System.exit(1);
            }
            
        }

        HTTPServer.main(port, listOfDirectories);
                
    } 


        
            
}
    


    
    

