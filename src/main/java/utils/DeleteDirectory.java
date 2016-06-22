/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author victorspringer
 */
public class DeleteDirectory {
    
    /**
     * 
     * @param file
     * @throws IOException 
     */
    public static void deleteDirectory(File file) throws IOException {
 
    	if(file.isDirectory()) {
    		//Directory is empty, then delete it
    		if(file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : " + file.getAbsolutePath());
    		} else {
                    //List all the directory contents
                    String files[] = file.list();
     
                    for (String temp : files) {
                        //Construct the file structure
                        File fileDelete = new File(file, temp);

                        //Recursive delete
                        deleteDirectory(fileDelete);
                    }
                    
        	   //Check the directory again, if empty then delete it
                    if(file.list().length == 0) {
                        file.delete();
                    }
    		}
    	} else {
            //If file, then delete it
            file.delete();
    	}
    }
}
