package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
