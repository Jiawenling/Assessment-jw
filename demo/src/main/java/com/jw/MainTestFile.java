package com.jw;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainTestFile {

    public static void main(String[] args) {
        String[] trialArray = new String[]{"--port","5000"};
        
        String directory = "/static";
        Integer port = 3000;
        if(trialArray!= null && trialArray.length >0){
            List<String> arguments = Arrays.asList(trialArray); 
            if(arguments.contains("--port")){
                Integer indexOfPort = arguments.indexOf("--port") + 1;
                port = Integer.parseInt(arguments.get(indexOfPort));
            }
            if(arguments.contains("--docRoot")){
                Integer indexOfDirectory = arguments.indexOf("--docRoot") + 1;
                directory = arguments.get(indexOfDirectory);
                if(directory.contains(":")){
                    String[] listOfDirectories = directory.split(":");
                    for (String items:listOfDirectories){
                        System.out.println("Directory is " + items);
                    }

                } 
                
            }
                
        } 
    
        System.out.println("Directory is " + directory);
        System.out.println("Port is "+port);

        File file = new File("/static");
        if(file.isDirectory()){
            System.out.println("Directory is present");
        } else{
            System.out.println("Not present");
        }
      
        
    }
   
}
