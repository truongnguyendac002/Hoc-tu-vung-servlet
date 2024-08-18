package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.Vocabulary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VocabularyDAO extends DAO {

    public List<Vocabulary> getVocabularyByTopicID(String topicID) {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        String sql = "SELECT v.* FROM Vocabulary v JOIN TopicVocabulary tv ON v.VocabularyID = tv.VocabularyID WHERE tv.TopicID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, topicID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vocabulary vocabulary = new Vocabulary(
                        rs.getString("VocabularyID"),
                        rs.getString("Word"),
                        rs.getString("Meaning"),
                        rs.getString("Pronunciation"),
                        rs.getString("Part_Of_Speech"),
                        rs.getString("Example_Sentence"),
                        rs.getString("ImageURL"),
                        rs.getInt("IsActive")
                );
                vocabularyList.add(vocabulary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vocabularyList;
    }
}
