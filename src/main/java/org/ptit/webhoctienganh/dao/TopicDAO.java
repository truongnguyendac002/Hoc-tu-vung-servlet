package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Topic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO extends DAO {

    public List<Topic> getTopicsByCourseID(String courseID) {
        List<Topic> topics = new ArrayList<>();
        String sql = "SELECT t.* FROM Topic t JOIN CourseTopic ct ON t.TopicID = ct.TopicID WHERE ct.CourseID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Topic topic = new Topic(
                        rs.getString("TopicID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ImageURL"),
                        rs.getInt("IsActive")
                );
                topics.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topics;
    }
}
