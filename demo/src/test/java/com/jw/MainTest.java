package com.jw;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MainTest {

    @Test
    public void testGetInputs(){
        String[] trialArray = new String[]{"--docRoot","/trial","--port","3000"};
        String path = "";
        String directory = "/static";
        Integer port = 0;
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
                } 
                
            }
                
        } 

        assertTrue(port==3000);
        System.out.println("Port is correct");
        assertTrue(directory.equals("/trial"));
        System.out.println("Directory is correct");
    }
    }
    

