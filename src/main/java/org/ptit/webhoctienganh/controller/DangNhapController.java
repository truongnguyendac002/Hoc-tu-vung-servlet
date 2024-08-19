package org.ptit.webhoctienganh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ptit.webhoctienganh.dao.AccountDAO;
import org.ptit.webhoctienganh.dao.StudentDAO;
import org.ptit.webhoctienganh.dao.TeacherDAO;
import org.ptit.webhoctienganh.model.Account;
import org.ptit.webhoctienganh.model.Student;
import org.ptit.webhoctienganh.model.Teacher;

import java.io.IOException;

@WebServlet("/dang-nhap")
public class DangNhapController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests to display the login page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
    }

    // Handle POST requests for login logic
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUsernameAndPassword(username, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            String role = account.getRole();

            if ("student".equals(role)) {
                // Retrieve the Student object based on the Account
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getStudentByAccountID(account.getAccountID());

                if (student != null) {
                    session.setAttribute("student", student);
                    response.sendRedirect("studenthome"); // Redirect to the student home page
                } else {
                    response.sendRedirect("dangnhap.jsp?error=2"); // Error if student not found
                }
            } else if ("teacher".equals(role)) {
                TeacherDAO teacherDAO = new TeacherDAO();
                Teacher teacher = teacherDAO.getTeacherByAccountID(account.getAccountID());

                if (teacher != null) {
                    session.setAttribute("teacher", teacher);
                    response.sendRedirect("teacherhome"); // Redirect to teacher home page
                } else {
                    response.sendRedirect("dangnhap.jsp?error=3"); // Error if teacher not found
                }
                // Redirect to teacher home page
            } else {
                response.sendRedirect("index.jsp"); // Default redirect if role is not recognized
            }
        } else {
            response.sendRedirect("dangnhap.jsp?error=1"); // Redirect back to login page with error
        }

        accountDAO.closeConnection();
    }
}
