<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.ptit.webhoctienganh.model.Vocabulary"%>
<%@page import="java.util.List"%>
<%@ page import="org.ptit.webhoctienganh.model.Student" %>
<%
    Student student = (Student) session.getAttribute("student");
    String courseID = (String) session.getAttribute("courseID");
    List<Vocabulary> reviewWords = (List<Vocabulary>) request.getAttribute("reviewWords");

    if (reviewWords == null || reviewWords.isEmpty()) {
        // Redirect to noWordsToReview.jsp if there are no words to review
        response.sendRedirect("noWordsToReview.jsp");
        return;
    }

    int currentIndex = (Integer) request.getAttribute("currentIndex");
    int totalWords = (Integer) request.getAttribute("totalWords");

    Vocabulary currentWord = reviewWords.get(currentIndex);
    int nextIndex = (currentIndex + 1) % reviewWords.size(); // Loop back to the first word if at the end
    boolean isLastWord = (currentIndex == reviewWords.size() - 1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ôn Tập Từ Vựng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .vocab-card {
            max-width: 500px;
            margin: 0 auto;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .vocab-image {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
        }
        .vocab-word {
            font-size: 24px;
            font-weight: bold;
            margin-top: 15px;
        }
        .feedback {
            font-size: 20px;
            margin-top: 15px;
            display: none;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Ôn Tập Từ Vựng</h2>
    <div class="vocab-card" id="vocabCard">
        <img src="<%= currentWord.getImageURL() %>" alt="Hình ảnh từ vựng" class="vocab-image">
        <div class="vocab-word"><%= currentWord.getWord() %></div>
        <input type="text" class="form-control mt-3" id="meaningInput" placeholder="Nhập nghĩa của từ">
        <button class="btn btn-primary mt-3 w-100" id="checkButton">Kiểm Tra</button>
        <div class="feedback mt-3" id="feedback"></div>
        <a href="review?index=<%= nextIndex %>" class="btn btn-success mt-3 w-100 hidden" id="nextButton">Tiếp Tục</a>
        <% if (isLastWord) { %>
        <a href="studenthome" class="btn btn-success mt-3 w-100 hidden" id="finishButton">Hoàn Thành</a>
        <% } %>
    </div>
</div>

<script>
    document.getElementById('checkButton').addEventListener('click', function() {
        var userInput = document.getElementById('meaningInput').value.trim().toLowerCase();
        var correctAnswer = "<%= currentWord.getMeaning() %>";
        var feedback = document.getElementById('feedback');
        var nextButton = document.getElementById('nextButton');
        var finishButton = document.getElementById('finishButton');

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "review", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onload = function() {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (userInput === correctAnswer.toLowerCase()) {
                    feedback.textContent = "Chính xác!";
                    feedback.classList.remove('text-danger');
                    feedback.classList.add('text-success');
                    document.getElementById('checkButton').style.display = 'none'; // Hide check button after correct answer
                    nextButton.classList.remove('hidden'); // Show next button if available
                    if (finishButton) {
                        finishButton.classList.remove('hidden'); // Show finish button if available
                    }
                } else {
                    feedback.textContent = "Sai rồi, nghĩa đúng là: " + correctAnswer;
                    nextButton.classList.remove('hidden'); // Show next button if available
                    feedback.classList.remove('text-success');
                    feedback.classList.add('text-danger');
                }
                feedback.style.display = 'block';
            }
        };
        xhr.send("studentID=<%= student.getStudentID() %>&courseID=<%= courseID %>&vocabularyID=<%= currentWord.getVocabularyID() %>&isCorrect=" + (userInput === correctAnswer.toLowerCase()));
    });
</script>
</body>
</html>
