package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends DAO {

    public List<Course> getCoursesByClassID(String classID) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.* FROM Course c JOIN ClassCourse cc ON c.CourseID = cc.CourseID WHERE cc.ClassID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, classID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                        rs.getString("CourseID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("ThumbnailURL"),
                        rs.getInt("IsActive")
                );
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}
