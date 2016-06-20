/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.ConferenceArticle;
import model.JournalArticle;
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
        
        String output = "";
        
        for(Resume resume : resumes) {
            List<ConferenceArticle> conferenceArticles = resume.getConferenceArticles();
            String countA1ConferenceArticle = countConferenceArticleLevel("A1", conferenceArticles);
            String countA2ConferenceArticle = countConferenceArticleLevel("A2", conferenceArticles);
            String countB1ConferenceArticle = countConferenceArticleLevel("B1", conferenceArticles);
            String countB2ConferenceArticle = countConferenceArticleLevel("B2", conferenceArticles);
            String countB3ConferenceArticle = countConferenceArticleLevel("B3", conferenceArticles);
            String countB4ConferenceArticle = countConferenceArticleLevel("B4", conferenceArticles);
            String countCConferenceArticle = countConferenceArticleLevel("C", conferenceArticles);
            String countNCConferenceArticle = countConferenceArticleLevel("N/C", conferenceArticles);
            
            List<JournalArticle> journalArticles = resume.getJournalArticles();
            String countA1JournalArticle = countJournalArticleLevel("A1", journalArticles);
            String countA2JournalArticle = countJournalArticleLevel("A2", journalArticles);
            String countB1JournalArticle = countJournalArticleLevel("B1", journalArticles);
            String countB2JournalArticle = countJournalArticleLevel("B2", journalArticles);
            String countB3JournalArticle = countJournalArticleLevel("B3", journalArticles);
            String countB4JournalArticle = countJournalArticleLevel("B4", journalArticles);
            String countCJournalArticle = countJournalArticleLevel("C", journalArticles);
            String countNCJournalArticle = countJournalArticleLevel("N/C", journalArticles);
            
            String countYearsDoctoralBankingParticipation = String
                    .valueOf(resume.getYearsDoctoralBankingParticipation().size());
            String countYearsMastersBankingParticipation = String
                    .valueOf(resume.getYearsMastersBankingParticipation().size());
            String countYearsUndergraduateBankingParticipation = String
                    .valueOf(resume.getYearsUndergraduateBankingParticipation().size());
            String countYearsConcludedDoctoralGuidance = String
                    .valueOf(resume.getYearsConcludedDoctoralGuidance().size());
            String countYearsConcludedMastersGuidance = String
                    .valueOf(resume.getYearsConcludedMastersGuidance().size());
            String countYearsConcludedUndergratuateGuidance = String
                    .valueOf(resume.getYearsConcludedUndergratuateGuidance().size());
            String countYearsInProgressDoctoralGuidance = String
                    .valueOf(resume.getYearsInProgressDoctoralGuidance().size());
            String countYearsInProgressMastersGuidance = String
                    .valueOf(resume.getYearsInProgressMastersGuidance().size());
            String countYearsInProgressUndergraduateGuidance = String
                    .valueOf(resume.getYearsInProgressUndergraduateGuidance().size());
            
            output += "Professor(a) " + resume.getProfessor().getName() + "\t"
                    + countA1JournalArticle + "\t"
                    + countA2JournalArticle + "\t"
                    + countB1JournalArticle + "\t"
                    + countB2JournalArticle + "\t"
                    + countB3JournalArticle + "\t"
                    + countB4JournalArticle + "\t"
                    + countCJournalArticle + "\t"
                    + countNCJournalArticle + "\t"
                    + countA1ConferenceArticle + "\t"
                    + countA2ConferenceArticle + "\t"
                    + countB1ConferenceArticle + "\t"
                    + countB2ConferenceArticle + "\t"
                    + countB3ConferenceArticle + "\t"
                    + countB4ConferenceArticle + "\t"
                    + countCConferenceArticle + "\t"
                    + countNCConferenceArticle + "\t"
                    + countYearsDoctoralBankingParticipation + "\t"
                    + countYearsMastersBankingParticipation + "\t"
                    + countYearsUndergraduateBankingParticipation + "\t"
                    + countYearsConcludedDoctoralGuidance + "\t"
                    + countYearsConcludedMastersGuidance + "\t"
                    + countYearsConcludedUndergratuateGuidance + "\t"
                    + countYearsInProgressDoctoralGuidance + "\t"
                    + countYearsInProgressMastersGuidance + "\t"
                    + countYearsInProgressUndergraduateGuidance
                    + "\n";
        }
        
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
