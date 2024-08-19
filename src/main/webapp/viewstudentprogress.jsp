<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="org.ptit.webhoctienganh.model.StudentProgress" %>
<%
    List<StudentProgress> studentProgressList = (List<StudentProgress>) request.getAttribute("studentProgressList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Student Progress</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Student Progress</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Total Words Learned</th>
            <th>Beginner Level</th>
            <th>Intermediate Level</th>
            <th>Advanced Level</th>
        </tr>
        </thead>
        <tbody>
        <% for (StudentProgress progress : studentProgressList) { %>
        <tr>
            <td><%= progress.getName() %></td>
            <td><%= progress.getTotalWords() %></td>
            <td><%= progress.getBeginnerWords() %></td>
            <td><%= progress.getIntermediateWords() %></td>
            <td><%= progress.getAdvancedWords() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <form action="teacherhome.jsp" method="get">
        <button class="btn btn-primary">Back to Home</button>
    </form>
</div>
</body>
</html>
