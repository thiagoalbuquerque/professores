/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.ConferenceArticle;
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
        
        List<LineOfResearch> lineOfResearchList = resumes.get(0).getProfessor().getLineOfResearch().getGraduationProgram().getLinesOfResearch();
        
        String output = "Professor\t\t\t"
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
                    + "Orientação Graduação Em Andamento\t"
                    + "\n\n";
        
        for(LineOfResearch lineOfResearch : lineOfResearchList) {
            for(Professor professor : lineOfResearch.getProfessors()) {
                List<ConferenceArticle> conferenceArticles = professor.getResume().getConferenceArticles();
                String countA1ConferenceArticle = countConferenceArticleLevel("A1", conferenceArticles);
                String countA2ConferenceArticle = countConferenceArticleLevel("A2", conferenceArticles);
                String countB1ConferenceArticle = countConferenceArticleLevel("B1", conferenceArticles);
                String countB2ConferenceArticle = countConferenceArticleLevel("B2", conferenceArticles);
                String countB3ConferenceArticle = countConferenceArticleLevel("B3", conferenceArticles);
                String countB4ConferenceArticle = countConferenceArticleLevel("B4", conferenceArticles);
                String countCConferenceArticle = countConferenceArticleLevel("C", conferenceArticles);
                String countNCConferenceArticle = countConferenceArticleLevel("N/C", conferenceArticles);

                List<JournalArticle> journalArticles = professor.getResume().getJournalArticles();
                String countA1JournalArticle = countJournalArticleLevel("A1", journalArticles);
                String countA2JournalArticle = countJournalArticleLevel("A2", journalArticles);
                String countB1JournalArticle = countJournalArticleLevel("B1", journalArticles);
                String countB2JournalArticle = countJournalArticleLevel("B2", journalArticles);
                String countB3JournalArticle = countJournalArticleLevel("B3", journalArticles);
                String countB4JournalArticle = countJournalArticleLevel("B4", journalArticles);
                String countCJournalArticle = countJournalArticleLevel("C", journalArticles);
                String countNCJournalArticle = countJournalArticleLevel("N/C", journalArticles);

                String countYearsDoctoralBankingParticipation = String
                        .valueOf(professor.getResume().getYearsDoctoralBankingParticipation().size());
                String countYearsMastersBankingParticipation = String
                        .valueOf(professor.getResume().getYearsMastersBankingParticipation().size());
                String countYearsUndergraduateBankingParticipation = String
                        .valueOf(professor.getResume().getYearsUndergraduateBankingParticipation().size());
                String countYearsConcludedDoctoralGuidance = String
                        .valueOf(professor.getResume().getYearsConcludedDoctoralGuidance().size());
                String countYearsConcludedMastersGuidance = String
                        .valueOf(professor.getResume().getYearsConcludedMastersGuidance().size());
                String countYearsConcludedUndergraduateGuidance = String
                        .valueOf(professor.getResume().getYearsConcludedUndergratuateGuidance().size());
                String countYearsInProgressDoctoralGuidance = String
                        .valueOf(professor.getResume().getYearsInProgressDoctoralGuidance().size());
                String countYearsInProgressMastersGuidance = String
                        .valueOf(professor.getResume().getYearsInProgressMastersGuidance().size());
                String countYearsInProgressUndergraduateGuidance = String
                        .valueOf(professor.getResume().getYearsInProgressUndergraduateGuidance().size());

                output += professor.getName() + "\t\t\t"
                        + countA1JournalArticle + "\t\t\t"
                        + countA2JournalArticle + "\t\t\t"
                        + countB1JournalArticle + "\t\t\t"
                        + countB2JournalArticle + "\t\t\t"
                        + countB3JournalArticle + "\t\t\t"
                        + countB4JournalArticle + "\t\t\t"
                        + countCJournalArticle + "\t\t\t"
                        + countNCJournalArticle + "\t\t\t"
                        + countA1ConferenceArticle + "\t\t\t"
                        + countA2ConferenceArticle + "\t\t\t"
                        + countB1ConferenceArticle + "\t\t\t"
                        + countB2ConferenceArticle + "\t\t\t"
                        + countB3ConferenceArticle + "\t\t\t"
                        + countB4ConferenceArticle + "\t\t\t"
                        + countCConferenceArticle + "\t\t\t"
                        + countNCConferenceArticle + "\t\t\t"
                        + countYearsDoctoralBankingParticipation + "\t\t\t"
                        + countYearsMastersBankingParticipation + "\t\t\t"
                        + countYearsUndergraduateBankingParticipation + "\t\t\t"
                        + countYearsConcludedDoctoralGuidance + "\t\t\t"
                        + countYearsConcludedMastersGuidance + "\t\t\t"
                        + countYearsConcludedUndergraduateGuidance + "\t\t\t"
                        + countYearsInProgressDoctoralGuidance + "\t\t\t"
                        + countYearsInProgressMastersGuidance + "\t\t\t"
                        + countYearsInProgressUndergraduateGuidance + "\n\n";
            }

            output += String.valueOf(lineOfResearch.getProfessors().size()) + "\n\n"
            + "\n\n\n";
        }
        
        output += String.valueOf(resumes.size());
        
        return output;
    }
    
    private String countConferenceArticleLevel(String level, List<ConferenceArticle> articleList) {
        
        Integer count = 0;
        for(ConferenceArticle article : articleList) {
            if(article.getArticleLevel().equals(level)) {
                count++;
            }
        }
        
        return count.toString();
    }
    
    private String countJournalArticleLevel(String level, List<JournalArticle> articleList) {
        
        Integer count = 0;
        for(JournalArticle article : articleList) {
            if(article.getArticleLevel().equals(level)) {
                count++;
            }
        }
        
        return count.toString();
    }
}
