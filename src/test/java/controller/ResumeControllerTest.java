/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Professor;
import model.Resume;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victorspringer
 */
public class ResumeControllerTest {
    
    public ResumeControllerTest() {
    }

    /**
     * Test of getResumesList method, of class ResumeController
     * @throws Exception 
     */
    @Test
    public void testGetResumesList() throws Exception {
        
        ResumeController instance = new ResumeController();
        
        //Testing parameters and its consequences
        List<Resume> result = instance.getResumesList(null, null, null);
        assertEquals(null, result);
        
        ProfessorController professorInstance = new ProfessorController();
        List<Professor> professors = professorInstance.getProfessorsList("teste");
        
        result = instance.getResumesList(professors, null, null);
        assertEquals(null, result);
        
        professors = professorInstance.getProfessorsList("ppgi-unirio");
        result = instance.getResumesList(professors, null, null);
        assertEquals(null, result);
        
        result = instance.getResumesList(professors, 2012, 2010);
        assertEquals(null, result);
        
        /*
        *This one is commented because it downloads and extract all professors zip files,
        *so it takes a long time. But it works!
        */
        /*
        result = instance.getResumesList(professors, 2012, 2016);
        assertTrue(result.size() > 0);
        
        //Tests if ALL attributes of ALL objects are filled.
        for(Resume resume : result) {
            assertTrue(resume.getGraduateProgram() != null);
            assertTrue(resume.getGraduateProgram().getName() != null);
            assertTrue(resume.getGraduateProgram().getLinesOfResearch() != null);
            assertFalse(resume.getGraduateProgram().getLinesOfResearch().contains(null));
            
            assertTrue(resume.getProfessor() != null);
            assertTrue(resume.getProfessor().getId() != null);
            assertTrue(resume.getProfessor().getName() != null);
            assertTrue(resume.getProfessor().getLineOfResearch() != null);
            assertTrue(resume.getProfessor().getResume() != null);
            
            assertTrue(resume.getConferenceArticles() != null);
            assertTrue(resume.getJournalArticles() != null);
            assertTrue(resume.getYearsConcludedDoctoralGuidance() != null);
            assertTrue(resume.getYearsConcludedMastersGuidance() != null);
            assertTrue(resume.getYearsConcludedUndergratuateGuidance() != null);
            assertTrue(resume.getYearsDoctoralBankingParticipation() != null);
            assertTrue(resume.getYearsMastersBankingParticipation() != null);
            assertTrue(resume.getYearsUndergraduateBankingParticipation() != null);
            assertTrue(resume.getYearsInProgressDoctoralGuidance() != null);
            assertTrue(resume.getYearsInProgressMastersGuidance() != null);
            assertTrue(resume.getYearsInProgressUndergraduateGuidance() != null);
            
            assertFalse(resume.getConferenceArticles().contains(null));
            assertFalse(resume.getJournalArticles().contains(null));
            assertFalse(resume.getYearsConcludedDoctoralGuidance().contains(null));
            assertFalse(resume.getYearsConcludedMastersGuidance().contains(null));
            assertFalse(resume.getYearsConcludedUndergratuateGuidance().contains(null));
            assertFalse(resume.getYearsDoctoralBankingParticipation().contains(null));
            assertFalse(resume.getYearsMastersBankingParticipation().contains(null));
            assertFalse(resume.getYearsUndergraduateBankingParticipation().contains(null));
            assertFalse(resume.getYearsInProgressDoctoralGuidance().contains(null));
            assertFalse(resume.getYearsInProgressMastersGuidance().contains(null));
            assertFalse(resume.getYearsInProgressUndergraduateGuidance().contains(null));
        }
        */
    }
    
}
