package org.ptit.webhoctienganh.model;

public class Topic {
    private String topicID;
    private String title;
    private String description;
    private String imageURL;
    private int isActive;

    // Constructor
    public Topic(String topicID, String title, String description, String imageURL, int isActive) {
        this.topicID = topicID;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
