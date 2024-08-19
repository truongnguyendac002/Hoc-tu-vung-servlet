package org.ptit.webhoctienganh.model;

public class Teacher {
    private String teacherID;
    private String name;
    private String email;
    private String phoneNumber;
    private String accountID; // This references the account associated with the teacher

    // Constructors
    public Teacher() {}

    public Teacher(String teacherID, String name, String email, String phoneNumber, String accountID) {
        this.teacherID = teacherID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountID = accountID;
    }

    // Getters and Setters
    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
