/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author victorspringer
 */
public class Article {
    
    private Integer year;
    private String articleClassification;
    private Resume resume;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getArticleClassification() {
        return articleClassification;
    }

    public void setArticleClassification(String articleClassification) {
        this.articleClassification = articleClassification;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
