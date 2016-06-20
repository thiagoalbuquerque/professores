/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author victorspringer
 */
public class LineOfResearch {
    
    private String name;
    private GraduationProgram graduationProgram;
    private List<Professor> professors;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public GraduationProgram getGraduationProgram() {
        return graduationProgram;
    }

    public void setGraduationProgram(GraduationProgram graduationProgram) {
        this.graduationProgram = graduationProgram;
    }
    
    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
