package org.ptit.webhoctienganh.model;

public class Course {
    private String courseID;
    private String name;
    private String description;
    private String thumbnailURL;
    private int isActive;

    // Constructor
    public Course(String courseID, String name, String description, String thumbnailURL, int isActive) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
