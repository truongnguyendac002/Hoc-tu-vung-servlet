package org.ptit.webhoctienganh.model;

import java.util.Date;

public class StudentVocabulary {
    private String studentID;
    private String vocabularyID;
    private String courseID;
    private Date nextReviewDate;
    private Date lastReviewedDate;
    private int proficiencyLevel;
    private float easinessFactor;
    private int timesReviewed;

    // Constructors, getters, and setters
    public StudentVocabulary(String studentID, String vocabularyID, String courseID, Date nextReviewDate,
                             Date lastReviewedDate, int proficiencyLevel, float easinessFactor, int timesReviewed) {
        this.studentID = studentID;
        this.vocabularyID = vocabularyID;
        this.courseID = courseID;
        this.nextReviewDate = nextReviewDate;
        this.lastReviewedDate = lastReviewedDate;
        this.proficiencyLevel = proficiencyLevel;
        this.easinessFactor = easinessFactor;
        this.timesReviewed = timesReviewed;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVocabularyID() {
        return vocabularyID;
    }

    public void setVocabularyID(String vocabularyID) {
        this.vocabularyID = vocabularyID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Date getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(Date nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public Date getLastReviewedDate() {
        return lastReviewedDate;
    }

    public void setLastReviewedDate(Date lastReviewedDate) {
        this.lastReviewedDate = lastReviewedDate;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public float getEasinessFactor() {
        return easinessFactor;
    }

    public void setEasinessFactor(float easinessFactor) {
        this.easinessFactor = easinessFactor;
    }

    public int getTimesReviewed() {
        return timesReviewed;
    }

    public void setTimesReviewed(int timesReviewed) {
        this.timesReviewed = timesReviewed;
    }
}
