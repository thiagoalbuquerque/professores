/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Professor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victorspringer
 */
public class ProfessorControllerTest {
    
    public ProfessorControllerTest() {
    }
    /**
     * Test of getProfessorsList method, of class ProfessorController.
     */
    @Test
    public void testGetProfessorsList() throws Exception {
        
        ProfessorController instance = new ProfessorController();
        
        List<Professor> result = instance.getProfessorsList("PPGI-UNIRIO");
        assertTrue(result.size() > 0);
        assertFalse(result.contains(null));
        
        result = instance.getProfessorsList("ppgi-unirio");
        assertTrue(result.size() > 0);
        
        //Returns null if professor is not found
        result = instance.getProfessorsList("PPG-I-UNIRIO");
        assertTrue(result == null);
    }
    
}
