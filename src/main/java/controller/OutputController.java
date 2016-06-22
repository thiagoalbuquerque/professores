/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import model.ConferenceArticle;
import model.GraduateProgram;
import model.JournalArticle;
import model.LineOfResearch;
import model.Professor;
import model.Resume;

/**
 *
 * @author victorspringer
 */
public class OutputController {
    
    private static OutputController outputControllerInstance;
    
    /**
     * 
     * @return OutputController instance
     */
    public static synchronized OutputController getInstance() {
        
        if (outputControllerInstance == null) {
            outputControllerInstance = new OutputController();
        }
        
        return outputControllerInstance;
    }
    
    /**
     * 
     * @param resumes
     * @return Output string to be saved into a text file
     */
    public String writeOutput(List<Resume> resumes) {
        
        if(resumes == null) {
            System.out.println("Forneça pelo menos um curriculo.");
            return null;
        }
        
        GraduateProgram graduateProgram = resumes.get(0).getGraduateProgram();
        List<LineOfResearch> lineOfResearchList = graduateProgram.getLinesOfResearch();
        
        Integer[] totalJournalArticleClassifications = new Integer[8];
        Integer[] totalLineOfResearchJournalArticleClassifications = new Integer[8];
        
        Integer[] totalConferenceArticleClassifications = new Integer[8];
        Integer[] totalLineOfResearchConferenceArticleClassifications = new Integer[8];
        
        Integer[] totalYearsCount = new Integer[9];
        Integer[] totalLineOfResearchYearsCount = new Integer[9];
        
        Integer[] totalLineOfResearchValues = new Integer[25];
        Integer[] totalValues = new Integer[25];
        
        Arrays.fill(totalValues, 0);
        
        StringBuilder output = new StringBuilder();
        output.append(getHeader());
        
        for(LineOfResearch lineOfResearch : lineOfResearchList) {
            
            Arrays.fill(totalJournalArticleClassifications, 0);
            Arrays.fill(totalConferenceArticleClassifications, 0);
            Arrays.fill(totalYearsCount, 0);
            Arrays.fill(totalLineOfResearchValues, 0);
                
            for(Professor professor : lineOfResearch.getProfessors()) {
                output.append(firstColumnWidth(professor.getName()));
                
                getValues(professor, totalLineOfResearchJournalArticleClassifications,
                        totalLineOfResearchConferenceArticleClassifications, totalLineOfResearchYearsCount);
                
                fillOutputColumns(output, totalLineOfResearchJournalArticleClassifications,
                        totalLineOfResearchConferenceArticleClassifications, totalLineOfResearchYearsCount,
                        totalLineOfResearchValues);
                
                output.append("\n");
            }
            
            output.append(firstColumnWidth("Total da Linha de Pesquisa '" + lineOfResearch.getName() + "'"));
            for(int i = 0; i < totalLineOfResearchValues.length; i++) {
                output.append(completeColumnWithWhiteSpaces(totalLineOfResearchValues[i].toString()));
                totalValues[i] += totalLineOfResearchValues[i];
            }
            
            output.append("\n\n");
        }
        
        output.append(firstColumnWidth("Total do Programa '" + graduateProgram.getName() + "'"));
        for(int i = 0; i < totalValues.length; i++) {
            output.append(completeColumnWithWhiteSpaces(totalValues[i].toString()));
        }
        
        output.append("\n");
        
        return output.toString();
    }
    
    /**
     * 
     * @return Header string for the output text file
     */
    private String getHeader() {
        return firstColumnWidth("Professor")
                + "Artigos Revistas A1 |  "
                + "Artigos Revistas A2  | "
                + " Artigos Revistas B1 | "
                + "Artigos Revistas B2 |  "
                + "Artigos Revistas B3  | "
                + " Artigos Revistas B4 | "
                + " Artigos Revistas C  | "
                + "Artigos Revistas N/C | "
                + " Artigos Eventos A1  | "
                + " Artigos Eventos A2  | "
                + " Artigos Eventos B1  | "
                + " Artigos Eventos B2  | "
                + "Artigos Eventos B3   | "
                + " Artigos Eventos B4  | "
                + " Artigos Eventos C  |  "
                + "Artigos Eventos N/C  | "
                + " Part. Banca Dout.  |  "
                + " Part. Banca Mest.  |  "
                + " Part. Banca Grad.  |  "
                + "Ori. Dout. Concluído | "
                + "Ori. Mest. Concluído | "
                + "Ori. Grad. Concluída | "
                + "Ori. Dout. Andamento | "
                + "Ori. Mest. Andamento | "
                + " Ori. Grad. Andamento  "
                + "\n\n";
    }
    
    /**
     * 
     * @param string
     * @return String with 75 characters containing the agument filled with white spaces
     */
    private String firstColumnWidth(String string) {
        int totalLength = 75;
        int stringLength = string.length();

        if(stringLength < totalLength) {
            for(int i = 0; i < totalLength - stringLength; i++) {
                string += " ";
            }
        }

        return string;
    }
    
    /**
     * 
     * @param professor
     * @param totalLineOfResearchJournalArticleClassifications
     * @param totalLineOfResearchConferenceArticleClassifications
     * @param totalLineOfResearchYearsCount 
     * Gets data of a professor
     */
    private void getValues(Professor professor, Integer[] totalLineOfResearchJournalArticleClassifications,
            Integer[] totalLineOfResearchConferenceArticleClassifications, Integer[] totalLineOfResearchYearsCount) {
        
        List<JournalArticle> journalArticles = professor.getResume().getJournalArticles();
        for(int i = 0; i < totalLineOfResearchJournalArticleClassifications.length; i++) {
            totalLineOfResearchJournalArticleClassifications[i] = getTotalLineOfResearchJournalArticleClassifications(journalArticles, i);
        }

        List<ConferenceArticle> conferenceArticles = professor.getResume().getConferenceArticles();
        for(int i = 0; i < totalLineOfResearchConferenceArticleClassifications.length; i++) {
            totalLineOfResearchConferenceArticleClassifications[i] = getTotalLineOfResearchConferenceArticleClassifications(conferenceArticles, i);
        }

        for(int i = 0; i < totalLineOfResearchYearsCount.length; i++) {
            totalLineOfResearchYearsCount[i] = getTotalLineOfResearchYearsCount(professor, i);
        }
    }
    
    /**
     * 
     * @param journalArticles List of journal articles
     * @param i Index of the article in the list
     * @return Classification of the article
     */
    private Integer getTotalLineOfResearchJournalArticleClassifications(List<JournalArticle> journalArticles, int i) {
        
        switch(i) {
            case 0:
                return countJournalArticleClassification("A1", journalArticles);
            case 1:
                return countJournalArticleClassification("A2", journalArticles);
            case 2:
                return countJournalArticleClassification("B1", journalArticles);
            case 3:
                return countJournalArticleClassification("B2", journalArticles);
            case 4:
                return countJournalArticleClassification("B3", journalArticles);
            case 5:
                return countJournalArticleClassification("B4", journalArticles);
            case 6:
                return countJournalArticleClassification("C", journalArticles);
            case 7:
                return countJournalArticleClassification("N/C", journalArticles);
            default:
                return 0;
        }
    }
    
    /**
     * 
     * @param conferenceArticles List of conference articles
     * @param i Index of the article in the list
     * @return Classification of the article
     */
    private Integer getTotalLineOfResearchConferenceArticleClassifications(List<ConferenceArticle> conferenceArticles, int i) {
        
        switch(i) {
            case 0:
                return countConferenceArticleClassification("A1", conferenceArticles);
            case 1:
                return countConferenceArticleClassification("A2", conferenceArticles);
            case 2:
                return countConferenceArticleClassification("B1", conferenceArticles);
            case 3:
                return countConferenceArticleClassification("B2", conferenceArticles);
            case 4:
                return countConferenceArticleClassification("B3", conferenceArticles);
            case 5:
                return countConferenceArticleClassification("B4", conferenceArticles);
            case 6:
                return countConferenceArticleClassification("C", conferenceArticles);
            case 7:
                return countConferenceArticleClassification("N/C", conferenceArticles);
            default:
                return 0;
        }
    }
    
    /**
     * 
     * @param classification
     * @param articleList
     * @return Quantity of journal articles of the list with the argument's classification
     */
    private Integer countJournalArticleClassification(String classification, List<JournalArticle> articleList) {
        
        Integer count = 0;
        for(JournalArticle article : articleList) {
            if(article.getArticleClassification().equals(classification)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * 
     * @param classification
     * @param articleList
     * @return Quantity of journal articles of the list with the argument's classification
     */
    private Integer countConferenceArticleClassification(String classification, List<ConferenceArticle> articleList) {
        
        Integer count = 0;
        for(ConferenceArticle article : articleList) {
            if(article.getArticleClassification().equals(classification)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * 
     * @param professor
     * @param i Index of the type of participation of the professor
     * @return Total of participations of the professor in the type determined by the index 
     */
    private Integer getTotalLineOfResearchYearsCount(Professor professor, int i) {
        
        switch(i) {
            case 0:
                return professor.getResume().getYearsDoctoralBankingParticipation().size();
            case 1:
                return professor.getResume().getYearsMastersBankingParticipation().size();
            case 2:
                return professor.getResume().getYearsUndergraduateBankingParticipation().size();
            case 3:
                return professor.getResume().getYearsConcludedDoctoralGuidance().size();
            case 4:
                return professor.getResume().getYearsConcludedMastersGuidance().size();
            case 5:
                return professor.getResume().getYearsConcludedUndergratuateGuidance().size();
            case 6:
                return professor.getResume().getYearsInProgressDoctoralGuidance().size();
            case 7:
                return professor.getResume().getYearsInProgressMastersGuidance().size();
            case 8:
                return professor.getResume().getYearsInProgressUndergraduateGuidance().size();
            default:
                return 0;
        }
    }
    
    /**
     * 
     * @param output
     * @param totalLineOfResearchJournalArticleClassifications
     * @param totalLineOfResearchConferenceArticleClassifications
     * @param totalLineOfResearchYearsCount
     * @param totalLineOfResearchValues 
     * Fill all the columns of the output string
     */
    private void fillOutputColumns(StringBuilder output, Integer[] totalLineOfResearchJournalArticleClassifications,
            Integer[] totalLineOfResearchConferenceArticleClassifications, Integer[] totalLineOfResearchYearsCount,
            Integer[] totalLineOfResearchValues) {
                
        int end = 0;
        for(int i = 0; i < totalLineOfResearchJournalArticleClassifications.length; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchJournalArticleClassifications[i].toString()));                    
            totalLineOfResearchValues[i] += totalLineOfResearchJournalArticleClassifications[i];
            end++;
        }

        int start = totalLineOfResearchJournalArticleClassifications.length;
        end += totalLineOfResearchConferenceArticleClassifications.length;
        int j = 0;
        for(int i = start; i < end; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchConferenceArticleClassifications[j].toString()));   
            totalLineOfResearchValues[i] += totalLineOfResearchConferenceArticleClassifications[j];
            j++;
        }

        start += totalLineOfResearchConferenceArticleClassifications.length;
        end += totalLineOfResearchYearsCount.length;
        j = 0;
        for(int i = start; i < end; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchYearsCount[j].toString()));
            totalLineOfResearchValues[i] += totalLineOfResearchYearsCount[j];
            j++;
        }
    }

    /**
     * 
     * @param string
     * @return String with 23 characters containing the agument filled with white spaces
     */
    private String completeColumnWithWhiteSpaces(String string) {
        
        String whiteSpaces = "";
        int times;
        
        switch(string.length()) {
            case 1:
                times = 11;
                break;
            case 2:
                times = 10;
                string += " ";
                break;
            case 3:
                times = 10;
                break;
            case 4:
                times = 9;
                string += " ";
                break;
            case 5:
                times = 9;
                break;
            default:
                times = 0;
                break;
        }
        
        for(int i = 0; i < times; i++) {
            whiteSpaces += " ";
        }

        return whiteSpaces + string + whiteSpaces;
    }
}
