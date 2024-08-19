package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Student;
import org.ptit.webhoctienganh.model.StudentProgress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DAO {

    // Method to retrieve a Student object based on AccountID
    public Student getStudentByAccountID(String accountID) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE AccountID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, accountID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone"),
                        rs.getDate("Date_Of_Birth"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getDate("Registration_Date"),
                        rs.getString("AccountID"),
                        rs.getString("ClassID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Method to retrieve all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("StudentID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone"),
                        rs.getDate("Date_Of_Birth"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getDate("Registration_Date"),
                        rs.getString("AccountID"),
                        rs.getString("ClassID")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to add a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (StudentID, First_Name, Last_Name, Phone, Date_Of_Birth, Gender, Address, Registration_Date, AccountID, ClassID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, student.getStudentID());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getPhone());
            stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
            stmt.setString(6, student.getGender());
            stmt.setString(7, student.getAddress());
            stmt.setDate(8, new Date(student.getRegistrationDate().getTime()));
            stmt.setString(9, student.getAccountID());
            stmt.setString(10, student.getClassID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing student
    public void update(Student student) {
        String sql = "UPDATE Student SET First_Name = ?, Last_Name = ?, phone = ?, Date_Of_Birth = ?, gender = ?, address = ? WHERE studentID = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getPhone());
            statement.setDate(4, student.getDateOfBirth());
            statement.setString(5, student.getGender());
            statement.setString(6, student.getAddress());
            statement.setString(7, student.getStudentID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentProgress> getAllStudentProgress() throws SQLException {
        List<StudentProgress> progressList = new ArrayList<>();


        String sql = "SELECT s.studentID, s.First_Name, s.Last_Name, " +
                "COUNT(sv.vocabularyID) as totalWords, " +
                "SUM(CASE WHEN sv.Proficiency_Level= 1 THEN 1 ELSE 0 END) as beginner, " +
                "SUM(CASE WHEN sv.Proficiency_Level = 2 THEN 1 ELSE 0 END) as intermediate, " +
                "SUM(CASE WHEN sv.Proficiency_Level = 3 THEN 1 ELSE 0 END) as advanced " +
                "FROM Student s " +
                "JOIN StudentVocabulary sv ON s.studentID = sv.studentID " +
                "GROUP BY s.First_Name, s.studentID, s.Last_Name";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            StudentProgress progress = new StudentProgress();
            progress.setName(rs.getString("First_Name") + " " + rs.getString("Last_Name"));
            progress.setTotalWords(rs.getInt("totalWords"));
            progress.setBeginnerWords(rs.getInt("beginner"));
            progress.setIntermediateWords(rs.getInt("intermediate"));
            progress.setAdvancedWords(rs.getInt("advanced"));
            progressList.add(progress);
        }

        return progressList;
    }

    // Method to delete a student
    public void deleteStudent(String studentID) {
        String sql = "DELETE FROM Student WHERE StudentID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, studentID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
