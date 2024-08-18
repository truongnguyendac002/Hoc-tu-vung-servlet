package org.ptit.webhoctienganh.controller.student;

import org.ptit.webhoctienganh.dao.StudentVocabularyDAO;
import org.ptit.webhoctienganh.model.Student;
import org.ptit.webhoctienganh.model.StudentVocabulary;
import org.ptit.webhoctienganh.model.Vocabulary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private final StudentVocabularyDAO studentVocabDAO = new StudentVocabularyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("student");
        String studentID = student != null ? student.getStudentID() : null;
        String courseID = (String) request.getSession().getAttribute("courseID");

        if (studentID == null || courseID == null) {
            response.sendRedirect("error.jsp"); // Redirect to an error page if session attributes are missing
            return;
        }

        List<Vocabulary> reviewWords = studentVocabDAO.getWordsForReview(studentID, courseID);

        if (reviewWords.isEmpty()) {
            // Redirect to a page showing a message if there are no words to review
            response.sendRedirect("noWordsToReview.jsp");
            return;
        }

        int index = 0;
        String indexParam = request.getParameter("index");
        if (indexParam != null) {
            try {
                index = Integer.parseInt(indexParam);
            } catch (NumberFormatException e) {
                index = 0; // Default to 0 if parsing fails
            }
        }

        // Ensure index is within bounds
        if (index >= reviewWords.size() || index < 0) {
            index = 0;
        }

        request.setAttribute("reviewWords", reviewWords);
        request.setAttribute("currentIndex", index);
        request.setAttribute("totalWords", reviewWords.size());

        request.getRequestDispatcher("review.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentID = request.getParameter("studentID");
        String courseID = request.getParameter("courseID");
        String vocabularyID = request.getParameter("vocabularyID");
        boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect"));

        StudentVocabulary currentStudentVocabulary = studentVocabDAO.getStudentVocabulary(studentID, courseID, vocabularyID);

        if (currentStudentVocabulary != null) {
            studentVocabDAO.updateStudentVocabulary(currentStudentVocabulary, isCorrect);
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
    }
}
