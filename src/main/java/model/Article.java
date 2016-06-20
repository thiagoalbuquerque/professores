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
    private String articleLevel;
    private Resume resume;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getArticleLevel() {
        return articleLevel;
    }

    public void setArticleLevel(String articleLevel) {
        this.articleLevel = articleLevel;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
