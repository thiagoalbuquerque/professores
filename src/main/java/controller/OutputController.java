/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Resume;

/**
 *
 * @author victorspringer
 */
public class OutputController {
    
    private static OutputController outputControllerInstance;
    
    public static synchronized OutputController getInstance() {
        
        if (outputControllerInstance == null) {
            outputControllerInstance = new OutputController();
        }
        
        return outputControllerInstance;
    }
    
    public String writeOutput(List<Resume> resumes) {
        
            return "Hello World!";
    }
}
