/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.ConferenceArticle;
import model.JournalArticle;
import model.Professor;
import model.Resume;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.DeleteDirectory;
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
    
    public List<Resume> getResumesList(List<Professor> professors, Integer startYear, Integer endYear) throws Exception {
        
        List<Resume> resumeList = new ArrayList<Resume>();
        
        for(Professor professor : professors) {
            File zipFile = getUnpackedFile(professor);
            
            if(zipFile != null) {
                File resumeFile = new File(new File("temp-resumes/curriculo.xml").getAbsolutePath());
                Resume resume = getSingleResume(resumeFile, startYear, endYear);
                resume.setProfessor(professor);
                professor.setResume(resume);
                
                resumeList.add(resume);
            }
        }
        
        //Delete temp directory with zip and curriculo.xml files
        File targetDir = new File(new File("temp-resumes").getAbsolutePath());
        DeleteDirectory.deleteDirectory(targetDir);
        
        return resumeList;
    }
    
    private File getUnpackedFile(Professor professor) throws Exception {
        
        String graduationProgramName = professor.getLineOfResearch().getGraduationProgram().getName();
        URL url = new URL("https://s3.amazonaws.com/posgraduacao/" + graduationProgramName + "/" + professor.getId() + ".zip");
        File targetDir = new File(new File("temp-resumes").getAbsolutePath());
        
        return Unzip.unpackArchive(url, targetDir);
    }
    
    private Resume getSingleResume(File resumeFile, Integer startYear, Integer endYear) throws Exception {
        
        return parseXML(resumeFile, startYear, endYear);
    }
    
    private Resume parseXML(File resumeFile, Integer startYear, Integer endYear) throws Exception {
        
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(resumeFile);
        
        document.getDocumentElement().normalize();
        
        NodeList conferenceArticlesNodeList = document.getElementsByTagName("TRABALHO-EM-EVENTOS");
        NodeList journalArticlesNodeList = document.getElementsByTagName("ARTIGO-PUBLICADO");
        NodeList yearsConcludedDoctoralGuidanceNodeList = document.getElementsByTagName("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO");
        NodeList yearsConcludedMastersGuidanceNodeList = document.getElementsByTagName("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO");
        NodeList yearsConcludedUndergraduateGuidanceNodeList = document.getElementsByTagName("OUTRAS-ORIENTACOES-CONCLUIDAS NATUREZA");
        NodeList yearsInProgressDoctoralGuidanceNodeList = document.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO");
        NodeList yearsInProgressMastersGuidanceNodeList = document.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO");
        NodeList yearsInProgressUndergraduateGuidanceNodeList = document.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-GRADUACAO");
        NodeList yearsDoctoralBankingParticipationNodeList = document.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO");
        NodeList yearsMastersBankingParticipationNodeList = document.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO");
        NodeList yearsUndergraduateBankingParticipationNodeList = document.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO");
        
        Resume resume = new Resume();
        
        List<ConferenceArticle> conferenceArticles = new ArrayList<ConferenceArticle>();
        List<JournalArticle> journalArticles = new ArrayList<JournalArticle>();
        List<Integer> yearsConcludedDoctoralGuidance = new ArrayList<Integer>();
        List<Integer> yearsConcludedMastersGuidance = new ArrayList<Integer>();
        List<Integer> yearsConcludedUndergratuateGuidance = new ArrayList<Integer>();
        List<Integer> yearsInProgressDoctoralGuidance = new ArrayList<Integer>();
        List<Integer> yearsInProgressMastersGuidance = new ArrayList<Integer>();
        List<Integer> yearsInProgressUndergraduateGuidance = new ArrayList<Integer>();
        List<Integer> yearsDoctoralBankingParticipation = new ArrayList<Integer>();
        List<Integer> yearsMastersBankingParticipation = new ArrayList<Integer>();
        List<Integer> yearsUndergraduateBankingParticipation = new ArrayList<Integer>();
        
        getConferenceArticles(conferenceArticles, conferenceArticlesNodeList, startYear, endYear, resume);
        getJournalArticles(journalArticles, journalArticlesNodeList, startYear, endYear, resume);
        getYears(yearsConcludedDoctoralGuidance, yearsConcludedDoctoralGuidanceNodeList, startYear, endYear);
        getYears(yearsConcludedMastersGuidance, yearsConcludedMastersGuidanceNodeList, startYear, endYear);
        getYearsConcludedUndergraduateGuidance(yearsConcludedUndergratuateGuidance, yearsConcludedUndergraduateGuidanceNodeList, startYear, endYear);
        getYears(yearsInProgressDoctoralGuidance, yearsInProgressDoctoralGuidanceNodeList, startYear, endYear);
        getYears(yearsInProgressMastersGuidance, yearsInProgressMastersGuidanceNodeList, startYear, endYear);
        getYears(yearsInProgressUndergraduateGuidance, yearsInProgressUndergraduateGuidanceNodeList, startYear, endYear);
        getYears(yearsDoctoralBankingParticipation, yearsDoctoralBankingParticipationNodeList, startYear, endYear);
        getYears(yearsMastersBankingParticipation, yearsMastersBankingParticipationNodeList, startYear, endYear);
        getYears(yearsUndergraduateBankingParticipation, yearsUndergraduateBankingParticipationNodeList, startYear, endYear);
        
        resume.setConferenceArticles(conferenceArticles);
        resume.setJournalArticles(journalArticles);
        resume.setYearsConcludedDoctoralGuidance(yearsConcludedDoctoralGuidance);
        resume.setYearsConcludedMastersGuidance(yearsConcludedMastersGuidance);
        resume.setYearsConcludedUndergratuateGuidance(yearsConcludedUndergratuateGuidance);
        resume.setYearsInProgressDoctoralGuidance(yearsInProgressDoctoralGuidance);
        resume.setYearsInProgressMastersGuidance(yearsInProgressMastersGuidance);
        resume.setYearsInProgressUndergraduateGuidance(yearsInProgressUndergraduateGuidance);
        resume.setYearsDoctoralBankingParticipation(yearsDoctoralBankingParticipation);
        resume.setYearsMastersBankingParticipation(yearsMastersBankingParticipation);
        resume.setYearsUndergraduateBankingParticipation(yearsUndergraduateBankingParticipation);
        
        return resume;
    }
    
    private void getConferenceArticles(List<ConferenceArticle> conferenceArticles, NodeList conferenceArticlesNodeList,
            Integer startYear, Integer endYear, Resume resume) throws Exception {
        
        NodeList entryList = getQualisXML();
        
        for(int i = 0; i < conferenceArticlesNodeList.getLength(); i++) {
            Node conferenceArticleNode = conferenceArticlesNodeList.item(i);
            
            if (conferenceArticleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element conferenceArticleElement = (Element) conferenceArticleNode;
                NodeList nodeList = conferenceArticleElement.getElementsByTagName("DETALHAMENTO-DO-TRABALHO");

                for(int j = 0; j < nodeList.getLength(); j++) {
                    Node node = nodeList.item(j);
                    
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        
                        Integer year = null;
                        if(!element.getAttribute("ANO-DE-REALIZACAO").isEmpty()) {
                            year = Integer.valueOf(element.getAttribute("ANO-DE-REALIZACAO"));
                        }

                        if(year != null && year >= startYear && year <= endYear) {
                            String conferenceName = element.getAttribute("NOME-DO-EVENTO");
                            ConferenceArticle conferenceArticle = new ConferenceArticle();

                            conferenceArticle.setConferenceName(conferenceName);
                            conferenceArticle.setYear(year);
                            conferenceArticle.setArticleLevel(getArticleLevel(conferenceName, entryList));
                            conferenceArticle.setResume(resume);
                            conferenceArticles.add(conferenceArticle);
                        }
                    }
                }
            }
        }
    }
    
    private void getJournalArticles(List<JournalArticle> journalArticles, NodeList journalArticlesNodeList,
            Integer startYear, Integer endYear, Resume resume) throws Exception {
        
        NodeList entryList = getQualisXML();
        
        for(int i = 0; i < journalArticlesNodeList.getLength(); i++) {
            Node journalArticleNode = journalArticlesNodeList.item(i);
            
            if (journalArticleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element journalArticleElement = (Element) journalArticleNode;
                NodeList nodeList = journalArticleElement.getElementsByTagName("DETALHAMENTO-DO-TRABALHO");

                for(int j = 0; j < nodeList.getLength(); j++) {
                    Node node = nodeList.item(j);
                    
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        
                        Integer year = null;
                        if(!element.getAttribute("ANO-DE-REALIZACAO").isEmpty()) {
                            year = Integer.valueOf(element.getAttribute("ANO-DE-REALIZACAO"));
                        }

                        if(year != null && year >= startYear && year <= endYear) {
                            String journalName = element.getAttribute("NOME-DO-EVENTO");
                            JournalArticle journalArticle = new JournalArticle();

                            journalArticle.setJournalName(journalName);
                            journalArticle.setYear(year);
                            journalArticle.setArticleLevel(getArticleLevel(journalName, entryList));
                            journalArticle.setResume(resume);
                            journalArticles.add(journalArticle);
                        }
                    }
                }
            }
        }
    }
    
    private NodeList getQualisXML() throws Exception {
        URL url = new URL("https://s3.amazonaws.com/posgraduacao/qualis.xml");
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(url.openStream());
        
        document.getDocumentElement().normalize();
        
        return document.getElementsByTagName("entry");
    }
    
    private String getArticleLevel(String conferenceName, NodeList entryList) throws Exception {
        
        for(int i = 0; i < entryList.getLength(); i++) {
            Node entry = entryList.item(i);
            
            if(entry.getNodeType() == Node.ELEMENT_NODE) {
                Element entryElement = (Element) entry;
                String regex = "(.*)" + entryElement.getAttribute("regex").toLowerCase() + "(.*)";
                if(conferenceName.toLowerCase().matches(regex)) {
                    return entryElement.getAttribute("class");
                }
            }
        }
        
        System.out.println("Veículo '" + conferenceName + "' não identificado.");
        return "N/C";
    }
    
    private void getYears(List<Integer> yearsList, NodeList nodeList, Integer startYear,Integer endYear) throws Exception {
    
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                Integer year = null;
                if(!element.getAttribute("ANO").isEmpty()) {
                    year = Integer.valueOf(element.getAttribute("ANO"));
                }

                if(year != null && year >= startYear && year <= endYear) {
                    yearsList.add(year);
                }
            }
        }
    }
    
    private void getYearsConcludedUndergraduateGuidance(List<Integer> yearsConcludedUndergraduateGuidance, NodeList yearsConcludedUndergraduateGuidanceNodeList, Integer startYear,Integer endYear) throws Exception {
    
        for(int i = 0; i < yearsConcludedUndergraduateGuidanceNodeList.getLength(); i++) {
            Node yearConcludedUndergraduateGuidanceNode = yearsConcludedUndergraduateGuidanceNodeList.item(i);
            
            if(yearConcludedUndergraduateGuidanceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element yearConcludedUndergraduateGuidanceElement = (Element) yearConcludedUndergraduateGuidanceNode;
                
                if(yearConcludedUndergraduateGuidanceElement.hasAttribute("TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO")) {
                    NodeList nodeList = yearConcludedUndergraduateGuidanceElement.getElementsByTagName("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS");
                
                    getYears(yearsConcludedUndergraduateGuidance, nodeList, startYear, endYear);
                }
            }
        }
    }
}
