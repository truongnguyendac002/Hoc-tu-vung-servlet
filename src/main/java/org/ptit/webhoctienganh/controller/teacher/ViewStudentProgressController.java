package org.ptit.webhoctienganh.controller.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ptit.webhoctienganh.dao.StudentDAO;
import org.ptit.webhoctienganh.model.StudentProgress;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/view-student-progress")
public class ViewStudentProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();

        // Retrieve the list of student progress data
        List<StudentProgress> studentProgressList = null;
        try {
            studentProgressList = studentDAO.getAllStudentProgress();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set the list of student progress as an attribute in the request
        request.setAttribute("studentProgressList", studentProgressList);

        // Forward the request to the JSP page
        request.getRequestDispatcher("viewstudentprogress.jsp").forward(request, response);
    }
}
