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

        List<StudentProgress> studentProgressList = null;
        try {
            studentProgressList = studentDAO.getAllStudentProgress();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("studentProgressList", studentProgressList);

        request.getRequestDispatcher("viewstudentprogress.jsp").forward(request, response);
    }
}
