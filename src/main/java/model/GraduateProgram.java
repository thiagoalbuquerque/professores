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
public class GraduateProgram {
    
    private String name;
    private List<LineOfResearch> linesOfResearch;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<LineOfResearch> getLinesOfResearch() {
        return linesOfResearch;
    }

    public void setLinesOfResearch(List<LineOfResearch> linesOfResearch) {
        this.linesOfResearch = linesOfResearch;
    }
}
