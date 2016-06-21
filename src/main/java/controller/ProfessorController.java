/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.GraduationProgram;
import model.LineOfResearch;
import model.Professor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author victorspringer
 */
public class ProfessorController {
    
    private static ProfessorController professorControllerInstance;
    
    public static synchronized ProfessorController getInstance() {
        
        if (professorControllerInstance == null) {
            professorControllerInstance = new ProfessorController();
        }
        
        return professorControllerInstance;
    }
    
    public List<Professor> getProfessorsList(String graduationProgramName) throws Exception {
        
        return parseXML(graduationProgramName);
    }
    
    private List<Professor> parseXML(String graduationProgramName) throws Exception {
        
        GraduationProgram graduationProgram = new GraduationProgram();
        graduationProgram.setName(graduationProgramName);
        
        URL url = new URL("https://s3.amazonaws.com/posgraduacao/" + graduationProgramName + "/contents.xml");
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(url.openStream());
        
        document.getDocumentElement().normalize();
        
        Node lineOfResearchListNode = document.getFirstChild();
            
        if (lineOfResearchListNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList linesOfResearch = lineOfResearchListNode.getChildNodes();
            List<LineOfResearch> lineOfResearchList = new ArrayList<LineOfResearch>();

            for (int j = 0; j < linesOfResearch.getLength(); j++) {
                Node lineOfResearchNode = linesOfResearch.item(j);

                if (lineOfResearchNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element lineOfResearchElement = (Element) lineOfResearchNode;
                    LineOfResearch lineOfResearch = new LineOfResearch();
                    
                    lineOfResearch.setName(lineOfResearchElement.getAttribute("nome"));
                    lineOfResearch.setGraduationProgram(graduationProgram);
                    
                    NodeList professorsNode = lineOfResearchNode.getChildNodes();
                    List<Professor> professors = new ArrayList<Professor>();
                    
                    for(int k = 0; k < professorsNode.getLength(); k++) {
                        Node professorNode = professorsNode.item(k);
                        
                        if(professorNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element professorElement = (Element) professorNode;
                            Professor professor = new Professor();
                            
                            professor.setId(professorElement.getAttribute("codigo"));
                            professor.setName(professorElement.getAttribute("nome"));
                            professor.setLineOfResearch(lineOfResearch);
                        
                            professors.add(professor);
                        }
                    }
                    
                    graduationProgram.setLinesOfResearch(lineOfResearchList);
                    lineOfResearch.setProfessors(professors);
                    lineOfResearchList.add(lineOfResearch);
                }
            }
            
            return mergeProfessorsList(lineOfResearchList);
        }
        
        return null;
    }
    
    private List<Professor> mergeProfessorsList(List<LineOfResearch> linesOfResearch) {
        
        List<Professor> professors = new ArrayList<Professor>();
        
        for(LineOfResearch lineOfResearch : linesOfResearch) {
            professors.addAll(lineOfResearch.getProfessors());
        }
        
        return professors;
    }
}
