/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.GraduateProgram;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author victorspringer
 */
public class GraduateProgramController {
    
    private static GraduateProgramController graduateProgramControllerInstance;
    
    /**
     * 
     * @return GraduateProgramController instance
     */
    public static synchronized GraduateProgramController getInstance() {
        
        if (graduateProgramControllerInstance == null) {
            graduateProgramControllerInstance = new GraduateProgramController();
        }
        
        return graduateProgramControllerInstance;
    }
    
    /**
     * 
     * @param name
     * @return GraduateProgram object
     * @throws Exception 
     */
    public GraduateProgram validateGraduateProgramName(String name) throws Exception {
        
            GraduateProgram graduateProgram = new GraduateProgram();
            String searchNameResult = graduateProgramNameSearch(name);
            
            graduateProgram.setName(searchNameResult);
            
            return graduateProgram;
    }
    
    /**
     * 
     * @param name validateGraduateProgramName argument
     * @return Result name from parseXML method
     * @throws Exception 
     */
    private String graduateProgramNameSearch(String name) throws Exception {
        
        if(name == null) {
            System.out.println("Forneça o nome do programa de pós-graduação corretamente.");
            return null;
        }
        
        return parseXML(name);
    }
    
    /**
     * 
     * @param name graduateProgramNameSearch argument
     * @return Result name of the XML parsing
     * @throws Exception 
     */
    private String parseXML(String name) throws Exception {
        
        URL url = new URL("https://s3.amazonaws.com/posgraduacao/programas.xml");
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(url.openStream());
        
        document.getDocumentElement().normalize();
        
        Node programList = document.getFirstChild();
            
        if (programList.getNodeType() == Node.ELEMENT_NODE) {

            NodeList programs = programList.getChildNodes();

            for (int j = 0; j < programs.getLength(); j++) {
                Node currentProgramNode = programs.item(j);

                if (currentProgramNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element programElement = (Element) currentProgramNode;

                    if(name.toUpperCase().equals(programElement.getAttribute("nome").toUpperCase())) {
                        return name.toUpperCase();
                    }
                }
            }
        }
        
        return null;
    }
}
