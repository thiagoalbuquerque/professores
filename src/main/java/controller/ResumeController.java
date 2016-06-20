/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.Professor;
import model.Resume;
import org.apache.commons.io.FileUtils;
import utils.Unzip;

/**
 *
 * @author victorspringer
 */
public class ResumeController {
    
    private static ResumeController resumeControllerInstance;
    
    public static synchronized ResumeController getInstance() {
        
        if (resumeControllerInstance == null) {
            resumeControllerInstance = new ResumeController();
        }
        
        return resumeControllerInstance;
    }
    
    public List<Resume> getResumesList(List<Professor> professors) throws Exception {
        
        List<Resume> resumeList = new ArrayList<Resume>();
        
        for(Professor professor : professors) {
            File resumeFile = getUnpackedFile(professor);
            
            if(resumeFile != null) {
                Resume resume = getSingleResume(resumeFile);
                resume.setProfessor(professor);
                
                professor.setResume(resume);
                resumeList.add(resume);
            }
        }
        
        //Delete temp directory with zip and curriculo.xml files
        File targetDir = new File(new File("temp-resumes").getAbsolutePath());
        FileUtils.deleteDirectory(targetDir);
        
        return resumeList;
    }
    
    private File getUnpackedFile(Professor professor) throws Exception {
        
        String graduationProgramName = professor.getLineOfResearch().getGraduationProgram().getName();
        URL url = new URL("https://s3.amazonaws.com/posgraduacao/" + graduationProgramName + "/" + professor.getId() + ".zip");
        File targetDir = new File(new File("temp-resumes").getAbsolutePath());
        
        return Unzip.unpackArchive(url, targetDir);
    }
    
    private Resume getSingleResume(File resumeFile) throws Exception {
        
        return parseXML(resumeFile);
    }
    
    private Resume parseXML(File resumeFile) throws Exception {
        
        //TO DO
        return new Resume();
    }
}
