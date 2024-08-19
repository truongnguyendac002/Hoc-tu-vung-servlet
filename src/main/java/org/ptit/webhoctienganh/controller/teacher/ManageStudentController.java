package org.ptit.webhoctienganh.controller.teacher;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ptit.webhoctienganh.dao.StudentDAO;
import org.ptit.webhoctienganh.model.Student;

@WebServlet("/manage-students")
public class ManageStudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of students from the database
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudents();

        // Set the students list as a request attribute
        request.setAttribute("students", students);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage_students.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle add, edit, delete actions based on the request parameters

        String action = request.getParameter("action");
        StudentDAO studentDAO = new StudentDAO();

        if ("add".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String dateOfBirth = request.getParameter("dateOfBirth"); // yyyy-MM-dd
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String accountID = request.getParameter("accountID");
            String classID = request.getParameter("classID");

            Student newStudent = new Student(
                    generateStudentID(), firstName, lastName, phone,
                    Date.valueOf(dateOfBirth), gender, address,
                    new Date(System.currentTimeMillis()), accountID, classID);

            studentDAO.addStudent(newStudent);
        } else if ("edit".equals(action)) {
            String studentID = request.getParameter("studentID");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            Date dateOfBirth = Date.valueOf( request.getParameter("dateOfBirth"));
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String classID = request.getParameter("classID");

            // Tạo một đối tượng Student mới với thông tin đã chỉnh sửa
            Student student = new Student(studentID, firstName, lastName, phone, dateOfBirth, gender, address,null,null, classID);

            // Sử dụng StudentDAO để cập nhật thông tin học viên trong cơ sở dữ liệu
            studentDAO.update(student);

            // Sau khi chỉnh sửa, chuyển hướng lại trang quản lý học viên
            response.sendRedirect("manage-students");

        } else if ("delete".equals(action)) {
            String studentID = request.getParameter("studentID");
            studentDAO.deleteStudent(studentID);
        }

    }

    private String generateStudentID() {
        // Implementation to generate a unique student ID
        // This can be based on database sequence, UUID, or custom logic
        return "ST" + System.currentTimeMillis(); // Example using timestamp
    }
}
