/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
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
            
                Integer totalLineOfResearchDoctoralBankingParticipation = 0;
                Integer totalLineOfResearchMastersBankingParticipation = 0;
                Integer totalLineOfResearchUndergraduateBankingParticipation = 0;
                Integer totalLineOfResearchConcludedDoctoralGuidance = 0;
                Integer totalLineOfResearchConcludedMastersGuidance = 0;
                Integer totalLineOfResearchConcludedUndergraduateGuidance = 0;
                Integer totalLineOfResearchInProgressDoctoralGuidance = 0;
                Integer totalLineOfResearchInProgressMastersGuidance = 0;
                Integer totalLineOfResearchInProgressUndergraduateGuidance = 0;
                Integer totalLineOfResearchA1ConferenceArticle = 0;
                Integer totalLineOfResearchA2ConferenceArticle = 0;
                Integer totalLineOfResearchB1ConferenceArticle = 0;
                Integer totalLineOfResearchB2ConferenceArticle = 0;
                Integer totalLineOfResearchB3ConferenceArticle = 0;
                Integer totalLineOfResearchB4ConferenceArticle = 0;
                Integer totalLineOfResearchCConferenceArticle = 0;
                Integer totalLineOfResearchNCConferenceArticle = 0;
                Integer totalLineOfResearchA1JournalArticle = 0;
                Integer totalLineOfResearchA2JournalArticle = 0;
                Integer totalLineOfResearchB1JournalArticle = 0;
                Integer totalLineOfResearchB2JournalArticle = 0;
                Integer totalLineOfResearchB3JournalArticle = 0;
                Integer totalLineOfResearchB4JournalArticle = 0;
                Integer totalLineOfResearchCJournalArticle = 0;
                Integer totalLineOfResearchNCJournalArticle = 0;
                
            for(Professor professor : lineOfResearch.getProfessors()) {
                
                Integer yearsDoctoralBankingParticipation = professor.getResume().getYearsDoctoralBankingParticipation().size();
                Integer yearsMastersBankingParticipation = professor.getResume().getYearsMastersBankingParticipation().size();
                Integer yearsUndergraduateBankingParticipation = professor.getResume().getYearsUndergraduateBankingParticipation().size();
                Integer yearsConcludedDoctoralGuidance = professor.getResume().getYearsConcludedDoctoralGuidance().size();
                Integer yearsConcludedMastersGuidance = professor.getResume().getYearsConcludedMastersGuidance().size();
                Integer yearsConcludedUndergraduateGuidance = professor.getResume().getYearsConcludedUndergratuateGuidance().size();
                Integer yearsInProgressDoctoralGuidance = professor.getResume().getYearsInProgressDoctoralGuidance().size();
                Integer yearsInProgressMastersGuidance = professor.getResume().getYearsInProgressMastersGuidance().size();
                Integer yearsInProgressUndergraduateGuidance = professor.getResume().getYearsInProgressUndergraduateGuidance().size();
                
                List<ConferenceArticle> conferenceArticles = professor.getResume().getConferenceArticles();
                Integer countA1ConferenceArticle = countConferenceArticleLevel("A1", conferenceArticles);
                Integer countA2ConferenceArticle = countConferenceArticleLevel("A2", conferenceArticles);
                Integer countB1ConferenceArticle = countConferenceArticleLevel("B1", conferenceArticles);
                Integer countB2ConferenceArticle = countConferenceArticleLevel("B2", conferenceArticles);
                Integer countB3ConferenceArticle = countConferenceArticleLevel("B3", conferenceArticles);
                Integer countB4ConferenceArticle = countConferenceArticleLevel("B4", conferenceArticles);
                Integer countCConferenceArticle = countConferenceArticleLevel("C", conferenceArticles);
                Integer countNCConferenceArticle = countConferenceArticleLevel("N/C", conferenceArticles);

                List<JournalArticle> journalArticles = professor.getResume().getJournalArticles();
                Integer countA1JournalArticle = countJournalArticleLevel("A1", journalArticles);
                Integer countA2JournalArticle = countJournalArticleLevel("A2", journalArticles);
                Integer countB1JournalArticle = countJournalArticleLevel("B1", journalArticles);
                Integer countB2JournalArticle = countJournalArticleLevel("B2", journalArticles);
                Integer countB3JournalArticle = countJournalArticleLevel("B3", journalArticles);
                Integer countB4JournalArticle = countJournalArticleLevel("B4", journalArticles);
                Integer countCJournalArticle = countJournalArticleLevel("C", journalArticles);
                Integer countNCJournalArticle = countJournalArticleLevel("N/C", journalArticles);

                String countYearsDoctoralBankingParticipation = String.valueOf(yearsDoctoralBankingParticipation);
                String countYearsMastersBankingParticipation = String.valueOf(yearsMastersBankingParticipation);
                String countYearsUndergraduateBankingParticipation = String.valueOf(yearsUndergraduateBankingParticipation);
                String countYearsConcludedDoctoralGuidance = String.valueOf(yearsConcludedDoctoralGuidance);
                String countYearsConcludedMastersGuidance = String.valueOf(yearsConcludedMastersGuidance);
                String countYearsConcludedUndergraduateGuidance = String.valueOf(yearsConcludedUndergraduateGuidance);
                String countYearsInProgressDoctoralGuidance = String.valueOf(yearsInProgressDoctoralGuidance);
                String countYearsInProgressMastersGuidance = String.valueOf(yearsInProgressMastersGuidance);
                String countYearsInProgressUndergraduateGuidance = String.valueOf(yearsInProgressUndergraduateGuidance);

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
                
                totalLineOfResearchDoctoralBankingParticipation += yearsDoctoralBankingParticipation;
                totalLineOfResearchMastersBankingParticipation += yearsMastersBankingParticipation;
                totalLineOfResearchUndergraduateBankingParticipation += yearsUndergraduateBankingParticipation;
                totalLineOfResearchConcludedDoctoralGuidance += yearsConcludedDoctoralGuidance;
                totalLineOfResearchConcludedMastersGuidance += yearsConcludedMastersGuidance;
                totalLineOfResearchConcludedUndergraduateGuidance += yearsConcludedUndergraduateGuidance;
                totalLineOfResearchInProgressDoctoralGuidance += yearsInProgressDoctoralGuidance;
                totalLineOfResearchInProgressMastersGuidance += yearsInProgressMastersGuidance;
                totalLineOfResearchInProgressUndergraduateGuidance += yearsInProgressUndergraduateGuidance;
                totalLineOfResearchA1ConferenceArticle += countA1ConferenceArticle;
                totalLineOfResearchA2ConferenceArticle += countA2ConferenceArticle;
                totalLineOfResearchB1ConferenceArticle += countB1ConferenceArticle;
                totalLineOfResearchB2ConferenceArticle += countB2ConferenceArticle;
                totalLineOfResearchB3ConferenceArticle += countB3ConferenceArticle;
                totalLineOfResearchB4ConferenceArticle += countB4ConferenceArticle;
                totalLineOfResearchCConferenceArticle += countCConferenceArticle;
                totalLineOfResearchNCConferenceArticle += countNCConferenceArticle;
                totalLineOfResearchA1JournalArticle += countA1JournalArticle;
                totalLineOfResearchA2JournalArticle += countA2JournalArticle;
                totalLineOfResearchB1JournalArticle += countB1JournalArticle;
                totalLineOfResearchB2JournalArticle += countB2JournalArticle;
                totalLineOfResearchB3JournalArticle += countB3JournalArticle;
                totalLineOfResearchB4JournalArticle += countB4JournalArticle;
                totalLineOfResearchCJournalArticle += countCJournalArticle;
                totalLineOfResearchNCJournalArticle += countNCJournalArticle;
            }

            output += "Total da Linha de Pesquisa '" + lineOfResearch.getName() + "'\t\t\t"
                + String.valueOf(totalLineOfResearchA1JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchA2JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB1JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB2JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB3JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB4JournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchCJournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchNCJournalArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchA1ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchA2ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB1ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB2ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB3ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchB4ConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchCConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchNCConferenceArticle) + "\t\t\t"
                + String.valueOf(totalLineOfResearchDoctoralBankingParticipation) + "\t\t\t"
                + String.valueOf(totalLineOfResearchMastersBankingParticipation) + "\t\t\t"
                + String.valueOf(totalLineOfResearchUndergraduateBankingParticipation) + "\t\t\t"
                + String.valueOf(totalLineOfResearchConcludedDoctoralGuidance) + "\t\t\t"
                + String.valueOf(totalLineOfResearchConcludedMastersGuidance) + "\t\t\t"
                + String.valueOf(totalLineOfResearchConcludedUndergraduateGuidance) + "\t\t\t"
                + String.valueOf(totalLineOfResearchInProgressDoctoralGuidance) + "\t\t\t"
                + String.valueOf(totalLineOfResearchInProgressMastersGuidance) + "\t\t\t"
                + String.valueOf(totalLineOfResearchInProgressUndergraduateGuidance) + "\n\n\n";
        }
        
        output += String.valueOf(resumes.size());
        
        return output;
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
    
    private Integer countJournalArticleLevel(String level, List<JournalArticle> articleList) {
        
        Integer count = 0;
        for(JournalArticle article : articleList) {
            if(article.getArticleLevel().equals(level)) {
                count++;
            }
        }
        
        return count;
    }
}
