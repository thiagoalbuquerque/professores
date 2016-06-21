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
                output.append(completeWithWhiteSpaces(professor.getName()));
                
                getValues(professor, totalLineOfResearchJournalArticleLevels,
                        totalLineOfResearchConferenceArticleLevels, totalLineOfResearchYearsCount);
                
                fillOutputColumns(output, totalLineOfResearchJournalArticleLevels,
                        totalLineOfResearchConferenceArticleLevels, totalLineOfResearchYearsCount,
                        totalLineOfResearchValues);
                
                output.append("\n");
            }
            
            output.append(completeWithWhiteSpaces("Total da Linha de Pesquisa '" + lineOfResearch.getName() + "'"));
            for(int i = 0; i < totalLineOfResearchValues.length; i++) {
                output.append(totalLineOfResearchValues[i].toString());
                if(i < totalLineOfResearchValues.length - 1) {
                    output.append("\t\t\t");
                }
                else {
                    output.append("\n\n");
                }
                
                totalValues[i] += totalLineOfResearchValues[i];
            }
        }
        
        output.append("Total do Programa '").append(graduationProgram.getName()).append("'\t\t\t");
        for(int i = 0; i < totalValues.length; i++) {
            output.append(totalValues[i].toString());
            if(i < totalLineOfResearchValues.length - 1) {
                output.append("\t\t\t");
            }
            else {
                output.append("\n");
            }
        }
        
        return output.toString();
    }
    
    private String getHeader() {
        return completeWithWhiteSpaces("Professor")
                + "Artigos Revistas A1\t"
                + "Artigos Revistas A2\t"
                + "Artigos Revistas B1\t"
                + "Artigos Revistas B2\t"
                + "Artigos Revistas B3\t"
                + "Artigos Revistas B4\t"
                + "Artigos Revistas C\t"
                + "Artigos Revistas N/C\t"
                + "Artigos Eventos A1\t"
                + "Artigos Eventos A2\t"
                + "Artigos Eventos B1\t"
                + "Artigos Eventos B2\t"
                + "Artigos Eventos B3\t"
                + "Artigos Eventos B4\t"
                + "Artigos Eventos C\t"
                + "Artigos Eventos N/C\t"
                + "Participação Banca de Doutorado\t"
                + "Participação Banca de Mestrado\t"
                + "Participação Banca de Graduação\t"
                + "Orientação Doutorado Concluído\t"
                + "Orientação Mestrado Concluído\t"
                + "Orientação Graduação Concluída\t"
                + "Orientação Doutorado Em Andamento\t"
                + "Orientação Mestrado Em Andamento\t"
                + "Orientação Graduação Em Andamento"
                + "\n\n";
    }
    
    private String completeWithWhiteSpaces(String word) {
        int totalLength = 75;

        if(word.length() < 75) {
            for(int i = 0; i < totalLength - word.length(); i++) {
                word += " ";
            }
        }

        return word;
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
            output.append(totalLineOfResearchJournalArticleLevels[i].toString()).append("\t\t\t");                    
            totalLineOfResearchValues[i] += totalLineOfResearchJournalArticleLevels[i];
            end++;
        }

        int start = totalLineOfResearchJournalArticleLevels.length;
        end += totalLineOfResearchConferenceArticleLevels.length;
        int j = 0;
        for(int i = start; i < end; i++) {
            output.append(totalLineOfResearchConferenceArticleLevels[j].toString()).append("\t\t\t");   
            totalLineOfResearchValues[i] = totalLineOfResearchConferenceArticleLevels[j];
            j++;
        }

        start += totalLineOfResearchConferenceArticleLevels.length;
        end += totalLineOfResearchYearsCount.length;
        j = 0;
        for(int i = start; i < end; i++) {
            output.append(totalLineOfResearchYearsCount[j].toString());
            if(i + 1 < end) {
                output.append("\t\t\t");
            }
            totalLineOfResearchValues[i] = totalLineOfResearchYearsCount[j];
            j++;
        }
    }
}
