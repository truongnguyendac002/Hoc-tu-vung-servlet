package org.ptit.webhoctienganh.model;

public class Vocabulary {
    private String vocabularyID;
    private String word;
    private String meaning;
    private String pronunciation;
    private String partOfSpeech;
    private String exampleSentence;
    private String imageURL;
    private int isActive;

    // Constructor
    public Vocabulary(String vocabularyID, String word, String meaning, String pronunciation,
                      String partOfSpeech, String exampleSentence, String imageURL, int isActive) {
        this.vocabularyID = vocabularyID;
        this.word = word;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.partOfSpeech = partOfSpeech;
        this.exampleSentence = exampleSentence;
        this.imageURL = imageURL;
        this.isActive = isActive;
    }

    public Vocabulary() {
        
    }

    // Getters and Setters
    public String getVocabularyID() {
        return vocabularyID;
    }

    public void setVocabularyID(String vocabularyID) {
        this.vocabularyID = vocabularyID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
