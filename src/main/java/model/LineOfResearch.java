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
    private GraduateProgram graduateProgram;
    private List<Professor> professors;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public GraduateProgram getGraduateProgram() {
        return graduateProgram;
    }

    public void setGraduateProgram(GraduateProgram graduateProgram) {
        this.graduateProgram = graduateProgram;
    }
    
    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
