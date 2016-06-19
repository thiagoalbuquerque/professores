/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.GraduationProgram;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author victorspringer
 */
public class GraduationProgramController {
    
    private static GraduationProgramController graduationProgramControllerInstance;
    
    public static synchronized GraduationProgramController getInstance() {
        if (graduationProgramControllerInstance == null) {
            graduationProgramControllerInstance = new GraduationProgramController();
        }
        
        return graduationProgramControllerInstance;
    }
    
    public GraduationProgram validateGraduationProgramName(String name) throws Exception {
        
            GraduationProgram graduationProgram = new GraduationProgram();
            String searchNameResult = graduationProgramNameSearch(name);
            
            graduationProgram.setName(searchNameResult);
            
            return graduationProgram;
    }
    
    private String graduationProgramNameSearch(String name) throws Exception {
        
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

                    if(name.toLowerCase().equals(programElement.getAttribute("nome").toLowerCase())) {
                        return name;
                    }
                }
            }
        }
        
        return null;
    }
}
