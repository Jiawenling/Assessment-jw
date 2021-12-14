package com.jw;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTestFile {

    

    public static void main(String[] args) {
       
        String[] trial = new String[]{"--port", "6600", "--docRoot", "real:stuff"};
        String directory = "";
        List<String> listOfDirectories = new ArrayList<>();
        Integer port = 3000;
        if(trial!= null && trial.length >0){
            List<String> arguments = Arrays.asList(trial); 
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
    System.out.println();
    System.out.println(listOfDirectories);

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
        
}


      

   
}
