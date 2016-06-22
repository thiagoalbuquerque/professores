/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.GraduateProgram;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victorspringer
 */
public class GraduateProgramControllerTest {
    
    public GraduateProgramControllerTest() {
    }

    /**
     * Tests if the object is found and if the returned name is the expected and uppercase
     * @throws Exception 
     */
    @Test
    public void testValidateGraduateProgramName() throws Exception {
        
        GraduateProgramController instance = new GraduateProgramController();
        
        GraduateProgram program = instance.validateGraduateProgramName(null);
        assertEquals(null, program.getName());
        
        program = instance.validateGraduateProgramName("ppgi-unirio");
        assertEquals("PPGI-UNIRIO", program.getName());
        assertNotSame("ppgi-UNIRIO", program.getName());
        assertNotSame("ppgi-unirio", program.getName());
        
        program = instance.validateGraduateProgramName("pPgI-uNiRiO");
        assertEquals("PPGI-UNIRIO", program.getName());
        assertNotSame("ppgi_unirio", program.getName());
        assertNotSame("pPgI-uNiRiO", program.getName());
        
        //Returns null if the graduate program is not found
        program = instance.validateGraduateProgramName("ppgiUnirio");
        assertNull(program.getName());
    }
}
