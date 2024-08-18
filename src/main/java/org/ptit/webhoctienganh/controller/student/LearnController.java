package org.ptit.webhoctienganh.controller.student;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ptit.webhoctienganh.dao.StudentVocabularyDAO;
import org.ptit.webhoctienganh.dao.VocabularyDAO;
import org.ptit.webhoctienganh.model.Student;
import org.ptit.webhoctienganh.model.StudentVocabulary;
import org.ptit.webhoctienganh.model.Vocabulary;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/learn")
public class LearnController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topicID = request.getParameter("topicID");
        int currentIndex = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 0;

        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        List<Vocabulary> vocabularyList = vocabularyDAO.getVocabularyByTopicID(topicID);

        Vocabulary currentWord = null;
        if (currentIndex < vocabularyList.size()) {
            currentWord = vocabularyList.get(currentIndex);
        } else {
            response.sendRedirect("studenthome.jsp");
            return;
        }

        request.setAttribute("currentWord", currentWord);
        request.setAttribute("topicID", topicID);
        request.setAttribute("currentIndex", currentIndex);
        request.getRequestDispatcher("learn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        String courseID = (String) session.getAttribute("courseID");
        String topicID = request.getParameter("topicID");
        int currentIndex = request.getParameter("index") != null ? Integer.parseInt(request.getParameter("index")) : 0;

        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        List<Vocabulary> vocabularyList = vocabularyDAO.getVocabularyByTopicID(topicID);

        if (currentIndex < vocabularyList.size()) {
            Vocabulary currentWord = vocabularyList.get(currentIndex);

            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.MINUTE, 5);
            Date nextReviewDate = calendar.getTime();

            StudentVocabulary studentVocab = new StudentVocabulary(
                    student.getStudentID(),
                    currentWord.getVocabularyID(),
                    (String) session.getAttribute("courseID"),
                    nextReviewDate,
                    now,
                    1,
                    2.5F,
                    1
            );

            StudentVocabularyDAO studentVocabDAO = new StudentVocabularyDAO();
            studentVocabDAO.updateStudentVocabulary(studentVocab);
        }

        if (currentIndex < vocabularyList.size() - 1) {
            response.sendRedirect("learn?topicID=" + topicID + "&index=" + (currentIndex + 1));
        } else {
            response.sendRedirect("topics.jsp?courseID="+courseID );
        }
    }
}
