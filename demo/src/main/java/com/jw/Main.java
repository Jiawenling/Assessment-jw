package com.jw;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        
        //get user input on port and directory
       
        String directory = "/static";
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
                    String[] listOfDirectories = directory.split(":");
                    for(String dir: listOfDirectories){
                        // checkDirExists(dir);
                    }
                } 
                
            }
                
        } 
    }


    
    
}
