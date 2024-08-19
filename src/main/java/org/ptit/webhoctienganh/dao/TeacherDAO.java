package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO extends DAO {

    public Teacher getTeacherByAccountID(String accountID) {
        Teacher teacher = null;
        String query = "SELECT * FROM Teacher WHERE accountID = ?";

        try (
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, accountID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherID(rs.getString("teacherID"));
                    teacher.setName(rs.getString("name"));
                    teacher.setEmail(rs.getString("email"));
                    teacher.setPhoneNumber(rs.getString("phoneNumber"));
                    teacher.setAccountID(rs.getString("accountID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacher;
    }
}
