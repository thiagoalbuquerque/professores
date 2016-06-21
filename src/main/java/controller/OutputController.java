/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import model.ConferenceArticle;
import model.GraduationProgram;
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
    
    public static synchronized OutputController getInstance() {
        
        if (outputControllerInstance == null) {
            outputControllerInstance = new OutputController();
        }
        
        return outputControllerInstance;
    }
    
    public String writeOutput(List<Resume> resumes) {
        
        GraduationProgram graduationProgram = resumes.get(0).getProfessor().getLineOfResearch().getGraduationProgram();
        List<LineOfResearch> lineOfResearchList = graduationProgram.getLinesOfResearch();
        
        Integer[] totalJournalArticleLevels = new Integer[8];
        Integer[] totalLineOfResearchJournalArticleLevels = new Integer[8];
        
        Integer[] totalConferenceArticleLevels = new Integer[8];
        Integer[] totalLineOfResearchConferenceArticleLevels = new Integer[8];
        
        Integer[] totalYearsCount = new Integer[9];
        Integer[] totalLineOfResearchYearsCount = new Integer[9];
        
        Integer[] totalLineOfResearchValues = new Integer[25];
        Integer[] totalValues = new Integer[25];
        
        Arrays.fill(totalValues, 0);
        
        StringBuilder output = new StringBuilder();
        output.append(getHeader());
        
        for(LineOfResearch lineOfResearch : lineOfResearchList) {
            
            Arrays.fill(totalJournalArticleLevels, 0);
            Arrays.fill(totalConferenceArticleLevels, 0);
            Arrays.fill(totalYearsCount, 0);
            Arrays.fill(totalLineOfResearchValues, 0);
                
            for(Professor professor : lineOfResearch.getProfessors()) {
                output.append(firstColumnWidth(professor.getName()));
                
                getValues(professor, totalLineOfResearchJournalArticleLevels,
                        totalLineOfResearchConferenceArticleLevels, totalLineOfResearchYearsCount);
                
                fillOutputColumns(output, totalLineOfResearchJournalArticleLevels,
                        totalLineOfResearchConferenceArticleLevels, totalLineOfResearchYearsCount,
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
        
        output.append(firstColumnWidth("Total do Programa '" + graduationProgram.getName() + "'"));
        for(int i = 0; i < totalValues.length; i++) {
            output.append(completeColumnWithWhiteSpaces(totalValues[i].toString()));
        }
        
        output.append("\n");
        
        return output.toString();
    }
    
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
    
    private void getValues(Professor professor, Integer[] totalLineOfResearchJournalArticleLevels,
            Integer[] totalLineOfResearchConferenceArticleLevels, Integer[] totalLineOfResearchYearsCount) {
        
        List<JournalArticle> journalArticles = professor.getResume().getJournalArticles();
        for(int i = 0; i < totalLineOfResearchJournalArticleLevels.length; i++) {
            totalLineOfResearchJournalArticleLevels[i] = getTotalLineOfResearchJournalArticleLevels(journalArticles, i);
        }

        List<ConferenceArticle> conferenceArticles = professor.getResume().getConferenceArticles();
        for(int i = 0; i < totalLineOfResearchConferenceArticleLevels.length; i++) {
            totalLineOfResearchConferenceArticleLevels[i] = getTotalLineOfResearchConferenceArticleLevels(conferenceArticles, i);
        }

        for(int i = 0; i < totalLineOfResearchYearsCount.length; i++) {
            totalLineOfResearchYearsCount[i] = getTotalLineOfResearchYearsCount(professor, i);
        }
    }
    
    private Integer getTotalLineOfResearchJournalArticleLevels(List<JournalArticle> journalArticles, int i) {
        
        switch(i) {
            case 0:
                return countJournalArticleLevel("A1", journalArticles);
            case 1:
                return countJournalArticleLevel("A2", journalArticles);
            case 2:
                return countJournalArticleLevel("B1", journalArticles);
            case 3:
                return countJournalArticleLevel("B2", journalArticles);
            case 4:
                return countJournalArticleLevel("B3", journalArticles);
            case 5:
                return countJournalArticleLevel("B4", journalArticles);
            case 6:
                return countJournalArticleLevel("C", journalArticles);
            case 7:
                return countJournalArticleLevel("N/C", journalArticles);
            default:
                return 0;
        }
    }
    
    private Integer getTotalLineOfResearchConferenceArticleLevels(List<ConferenceArticle> conferenceArticles, int i) {
        
        switch(i) {
            case 0:
                return countConferenceArticleLevel("A1", conferenceArticles);
            case 1:
                return countConferenceArticleLevel("A2", conferenceArticles);
            case 2:
                return countConferenceArticleLevel("B1", conferenceArticles);
            case 3:
                return countConferenceArticleLevel("B2", conferenceArticles);
            case 4:
                return countConferenceArticleLevel("B3", conferenceArticles);
            case 5:
                return countConferenceArticleLevel("B4", conferenceArticles);
            case 6:
                return countConferenceArticleLevel("C", conferenceArticles);
            case 7:
                return countConferenceArticleLevel("N/C", conferenceArticles);
            default:
                return 0;
        }
    }
    
    private Integer countJournalArticleLevel(String level, List<JournalArticle> articleList) {
        
        Integer count = 0;
        for(JournalArticle article : articleList) {
            if(article.getArticleLevel().equals(level)) {
                count++;
            }
        }
        
        return count;
    }
    
    private Integer countConferenceArticleLevel(String level, List<ConferenceArticle> articleList) {
        
        Integer count = 0;
        for(ConferenceArticle article : articleList) {
            if(article.getArticleLevel().equals(level)) {
                count++;
            }
        }
        
        return count;
    }
    
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
    
    private void fillOutputColumns(StringBuilder output, Integer[] totalLineOfResearchJournalArticleLevels,
            Integer[] totalLineOfResearchConferenceArticleLevels, Integer[] totalLineOfResearchYearsCount,
            Integer[] totalLineOfResearchValues) {
                
        int end = 0;
        for(int i = 0; i < totalLineOfResearchJournalArticleLevels.length; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchJournalArticleLevels[i].toString()));                    
            totalLineOfResearchValues[i] += totalLineOfResearchJournalArticleLevels[i];
            end++;
        }

        int start = totalLineOfResearchJournalArticleLevels.length;
        end += totalLineOfResearchConferenceArticleLevels.length;
        int j = 0;
        for(int i = start; i < end; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchConferenceArticleLevels[j].toString()));   
            totalLineOfResearchValues[i] += totalLineOfResearchConferenceArticleLevels[j];
            j++;
        }

        start += totalLineOfResearchConferenceArticleLevels.length;
        end += totalLineOfResearchYearsCount.length;
        j = 0;
        for(int i = start; i < end; i++) {
            output.append(completeColumnWithWhiteSpaces(totalLineOfResearchYearsCount[j].toString()));
            totalLineOfResearchValues[i] += totalLineOfResearchYearsCount[j];
            j++;
        }
    }

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
