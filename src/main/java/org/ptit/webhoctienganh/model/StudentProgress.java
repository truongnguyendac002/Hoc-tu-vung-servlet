package org.ptit.webhoctienganh.model;

public class StudentProgress {
    private String name;
    private int totalWords;
    private int beginnerWords;
    private int intermediateWords;
    private int advancedWords;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getBeginnerWords() {
        return beginnerWords;
    }

    public void setBeginnerWords(int beginnerWords) {
        this.beginnerWords = beginnerWords;
    }

    public int getIntermediateWords() {
        return intermediateWords;
    }

    public void setIntermediateWords(int intermediateWords) {
        this.intermediateWords = intermediateWords;
    }

    public int getAdvancedWords() {
        return advancedWords;
    }

    public void setAdvancedWords(int advancedWords) {
        this.advancedWords = advancedWords;
    }
}
