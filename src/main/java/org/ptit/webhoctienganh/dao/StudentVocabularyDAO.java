package org.ptit.webhoctienganh.dao;

import org.ptit.webhoctienganh.model.StudentVocabulary;
import org.ptit.webhoctienganh.model.Vocabulary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentVocabularyDAO extends DAO {

    public StudentVocabulary getStudentVocabulary(String studentID, String courseID, String vocabularyID) {
        StudentVocabulary studentVocabulary = null;
        String sql = "SELECT * FROM StudentVocabulary WHERE StudentID = ? AND CourseID = ? AND VocabularyID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ps.setString(2, courseID);
            ps.setString(3, vocabularyID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                studentVocabulary = new StudentVocabulary(
                        rs.getString("StudentID"),
                        rs.getString("VocabularyID"),
                        rs.getString("CourseID"),
                        rs.getDate("Next_Review_Date"),
                        rs.getDate("Last_Reviewed_Date"),
                        rs.getInt("Proficiency_Level"),
                        rs.getFloat("Easiness_Factor"),
                        rs.getInt("TimesReviewed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVocabulary;
    }

    public void updateStudentVocabulary(StudentVocabulary studentVocabulary) {
        String sql = "INSERT INTO StudentVocabulary (StudentID, VocabularyID, CourseID, Next_Review_Date, Last_Reviewed_Date, " +
                "Proficiency_Level, Easiness_Factor, TimesReviewed) VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Next_Review_Date = ?, Last_Reviewed_Date = ?, Proficiency_Level = ?, Easiness_Factor = ?, TimesReviewed = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentVocabulary.getStudentID());
            stmt.setString(2, studentVocabulary.getVocabularyID());
            stmt.setString(3, studentVocabulary.getCourseID());
            stmt.setDate(4, new java.sql.Date(studentVocabulary.getNextReviewDate().getTime()));
            stmt.setDate(5, new java.sql.Date(studentVocabulary.getLastReviewedDate().getTime()));
            stmt.setInt(6, studentVocabulary.getProficiencyLevel());
            stmt.setFloat(7, studentVocabulary.getEasinessFactor());
            stmt.setInt(8, studentVocabulary.getTimesReviewed());

            stmt.setDate(9, new java.sql.Date(studentVocabulary.getNextReviewDate().getTime()));
            stmt.setDate(10, new java.sql.Date(studentVocabulary.getLastReviewedDate().getTime()));
            stmt.setInt(11, studentVocabulary.getProficiencyLevel());
            stmt.setFloat(12, studentVocabulary.getEasinessFactor());
            stmt.setInt(13, studentVocabulary.getTimesReviewed());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vocabulary> getWordsForReview(String studentID, String courseID) {
        List<Vocabulary> reviewWords = new ArrayList<>();
        String sql = "SELECT v.* FROM StudentVocabulary sv "
                + "JOIN Vocabulary v ON sv.vocabularyID = v.vocabularyID "
                + "JOIN Course c ON sv.courseID = c.courseID "
                + "WHERE sv.studentID = ? AND sv.courseID = ? AND sv.Next_Review_Date <= CURRENT_TIMESTAMP";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ps.setString(2, courseID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vocabulary vocab = new Vocabulary();
                vocab.setVocabularyID(rs.getString("vocabularyID"));
                vocab.setWord(rs.getString("word"));
                vocab.setMeaning(rs.getString("meaning"));
                vocab.setImageURL(rs.getString("imageURL"));
                vocab.setPronunciation(rs.getString("pronunciation"));
                reviewWords.add(vocab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviewWords;
    }

    public void updateStudentVocabulary(StudentVocabulary studentVocabulary, boolean isCorrect) {
        String sql = "INSERT INTO StudentVocabulary (StudentID, VocabularyID, CourseID, Next_Review_Date, Last_Reviewed_Date, " +
                "Proficiency_Level, Easiness_Factor, TimesReviewed) VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Next_Review_Date = ?, Last_Reviewed_Date = ?, Proficiency_Level = ?, Easiness_Factor = ?, TimesReviewed = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Retrieve existing data
            String vocabID = studentVocabulary.getVocabularyID();
            String courseID = studentVocabulary.getCourseID();
            String studentID = studentVocabulary.getStudentID();

            // Calculate new values based on SM2 algorithm
            int newProficiencyLevel = studentVocabulary.getProficiencyLevel();
            float newEasinessFactor = studentVocabulary.getEasinessFactor();
            int newTimesReviewed = studentVocabulary.getTimesReviewed() + 1;
            Date newNextReviewDate;

            if (isCorrect) {
                // Update based on SM2 algorithm
                if (studentVocabulary.getEasinessFactor() < 1.3) {
                    newEasinessFactor = 1.3f;
                } else {
                    newEasinessFactor = studentVocabulary.getEasinessFactor() + 0.1f - (0.08f * (studentVocabulary.getProficiencyLevel() - 3));
                }

                int interval = (int) (Math.pow(2, newProficiencyLevel) * newEasinessFactor);
                newNextReviewDate = new Date(System.currentTimeMillis() + interval * 24 * 60 * 60 * 1000); // Interval in days

                newProficiencyLevel++;
            } else {
                newEasinessFactor = studentVocabulary.getEasinessFactor() - 0.2f;
                if (newEasinessFactor < 1.3) newEasinessFactor = 1.3f;

                newProficiencyLevel = 0;
                newNextReviewDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000); // Review tomorrow
            }

            // Update database
            stmt.setString(1, studentID);
            stmt.setString(2, vocabID);
            stmt.setString(3, courseID);
            stmt.setDate(4, new java.sql.Date(newNextReviewDate.getTime()));
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(6, newProficiencyLevel);
            stmt.setFloat(7, newEasinessFactor);
            stmt.setInt(8, newTimesReviewed);

            stmt.setDate(9, new java.sql.Date(newNextReviewDate.getTime()));
            stmt.setDate(10, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(11, newProficiencyLevel);
            stmt.setFloat(12, newEasinessFactor);
            stmt.setInt(13, newTimesReviewed);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
