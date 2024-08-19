package org.ptit.webhoctienganh.model;

import java.sql.Date;

public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private String phone;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private Date registrationDate;
    private String accountID;
    private String classID;

    // Constructor
    public Student(String studentID, String firstName, String lastName, String phone, Date dateOfBirth,
                   String gender, String address, Date registrationDate, String accountID, String classID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.registrationDate = registrationDate;
        this.accountID = accountID;
        this.classID = classID;
    }

    // Getters and Setters
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public java.sql.Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public String getAccountID() { return accountID; }
    public void setAccountID(String accountID) { this.accountID = accountID; }

    public String getClassID() { return classID; }
    public void setClassID(String classID) { this.classID = classID; }
}
