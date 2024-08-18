<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="org.ptit.webhoctienganh.model.Topic" %>
<%@page import="org.ptit.webhoctienganh.dao.TopicDAO" %>
<%@page import="java.util.List" %>
<%
    // Retrieve courseID from the request parameter
    String courseID = request.getParameter("courseID");
    session.setAttribute("courseID", courseID);
    // Retrieve the list of topics for the selected course
    TopicDAO topicDAO = new TopicDAO();
    List<Topic> topics = topicDAO.getTopicsByCourseID(courseID);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Chủ Đề</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center">
        <h2 class="mb-4">Chọn Chủ Đề</h2>
        <a href="review" class="btn btn-danger">Ôn Tập Từ Vựng</a>
    </div>
    <div class="list-group">
        <% for (Topic topic : topics) { %>
        <a href="learn?topicID=<%= topic.getTopicID() %>&index=0"
           class="list-group-item list-group-item-action d-flex align-items-center">
            <img src="<%= topic.getImageURL() %>" alt="Thumbnail <%= topic.getTitle() %>" class="img-thumbnail me-3"
                 style="width: 100px;">
            <div><%= topic.getTitle() %>
            </div>
        </a>
        <% } %>
    </div>
</div>
</body>
</html>
