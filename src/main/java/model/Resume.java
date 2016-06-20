/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author victorspringer
 */
public class Resume {
    
    private Professor professor;
    private List<ConferenceArticle> conferenceArticles;
    private List<JournalArticle> journalArticles;
    private List<Integer> yearsConcludedDoctoralGuidance;
    private List<Integer> yearsConcludedMastersGuidance;
    private List<Integer> yearsConcludedUndergratuateGuidance;
    private List<Integer> yearsInProgressDoctoralGuidance;
    private List<Integer> yearsInProgressMastersGuidance;
    private List<Integer> yearsInProgressUndergraduateGuidance;
    private List<Integer> yearsDoctoralBankingParticipation;
    private List<Integer> yearsMastersBankingParticipation;
    private List<Integer> yearsUndergraduateBankingParticipation;
    
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public List<ConferenceArticle> getConferenceArticles() {
        return conferenceArticles;
    }

    public void setConferenceArticles(List<ConferenceArticle> conferenceArticles) {
        this.conferenceArticles = conferenceArticles;
    }
    
    public List<JournalArticle> getJournalArticles() {
        return journalArticles;
    }

    public void setJournalArticles(List<JournalArticle> journalArticles) {
        this.journalArticles = journalArticles;
    }

    public List<Integer> getYearsConcludedDoctoralGuidance() {
        return yearsConcludedDoctoralGuidance;
    }

    public void setYearsConcludedDoctoralGuidance(List<Integer> yearsConcludedDoctoralGuidance) {
        this.yearsConcludedDoctoralGuidance = yearsConcludedDoctoralGuidance;
    }

    public List<Integer> getYearsConcludedMastersGuidance() {
        return yearsConcludedMastersGuidance;
    }

    public void setYearsConcludedMastersGuidance(List<Integer> yearsConcludedMastersGuidance) {
        this.yearsConcludedMastersGuidance = yearsConcludedMastersGuidance;
    }

    public List<Integer> getYearsConcludedUndergratuateGuidance() {
        return yearsConcludedUndergratuateGuidance;
    }

    public void setYearsConcludedUndergratuateGuidance(List<Integer> yearsConcludedUndergratuateGuidance) {
        this.yearsConcludedUndergratuateGuidance = yearsConcludedUndergratuateGuidance;
    }

    public List<Integer> getYearsInProgressDoctoralGuidance() {
        return yearsInProgressDoctoralGuidance;
    }

    public void setYearsInProgressDoctoralGuidance(List<Integer> yearsInProgressDoctoralGuidance) {
        this.yearsInProgressDoctoralGuidance = yearsInProgressDoctoralGuidance;
    }

    public List<Integer> getYearsInProgressMastersGuidance() {
        return yearsInProgressMastersGuidance;
    }

    public void setYearsInProgressMastersGuidance(List<Integer> yearsInProgressMastersGuidance) {
        this.yearsInProgressMastersGuidance = yearsInProgressMastersGuidance;
    }

    public List<Integer> getYearsInProgressUndergraduateGuidance() {
        return yearsInProgressUndergraduateGuidance;
    }

    public void setYearsInProgressUndergraduateGuidance(List<Integer> yearsInProgressUndergraduateGuidance) {
        this.yearsInProgressUndergraduateGuidance = yearsInProgressUndergraduateGuidance;
    }

    public List<Integer> getYearsDoctoralBankingParticipation() {
        return yearsDoctoralBankingParticipation;
    }

    public void setYearsDoctoralBankingParticipation(List<Integer> yearsDoctoralBankingParticipation) {
        this.yearsDoctoralBankingParticipation = yearsDoctoralBankingParticipation;
    }

    public List<Integer> getYearsMastersBankingParticipation() {
        return yearsMastersBankingParticipation;
    }

    public void setYearsMastersBankingParticipation(List<Integer> yearsMastersBankingParticipation) {
        this.yearsMastersBankingParticipation = yearsMastersBankingParticipation;
    }

    public List<Integer> getYearsUndergraduateBankingParticipation() {
        return yearsUndergraduateBankingParticipation;
    }

    public void setYearsUndergraduateBankingParticipation(List<Integer> yearsUndergraduateBankingParticipation) {
        this.yearsUndergraduateBankingParticipation = yearsUndergraduateBankingParticipation;
    }
}
