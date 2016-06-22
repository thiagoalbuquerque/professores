/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victorspringer
 */
public class OutputControllerTest {
    
    public OutputControllerTest() {
    }
    
    /**
     * Test of writeOutput method, of class OutputController
     */
    @Test
    public void testWriteOutput() {
        
        OutputController instance = new OutputController();
        
        String result = instance.writeOutput(null);
        assertEquals(null, result);
    }
    
}
