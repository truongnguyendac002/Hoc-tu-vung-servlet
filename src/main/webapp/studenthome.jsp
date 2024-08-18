<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.ptit.webhoctienganh.model.Course"%>
<%@page import="org.ptit.webhoctienganh.model.Student"%>
<%@page import="org.ptit.webhoctienganh.dao.CourseDAO"%>
<%@page import="java.util.List"%>
<%
    // Get the Student object from the session
    Student student = (Student) session.getAttribute("student");

    // Retrieve the list of courses for the student's class
    CourseDAO courseDAO = new CourseDAO();
    List<Course> courses = courseDAO.getCoursesByClassID(student.getClassID());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Khóa Học</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Chọn Khóa Học</h2>
    <div class="list-group">
        <% for (Course course : courses) { %>
        <a href="topics.jsp?courseID=<%= course.getCourseID() %>" class="list-group-item list-group-item-action d-flex align-items-center">
            <img src="<%= course.getThumbnailURL() %>" alt="Thumbnail <%= course.getName() %>" class="img-thumbnail me-3" style="width: 100px;">
            <div><%= course.getName() %></div>
        </a>
        <% } %>
    </div>
</div>
</body>
</html>
