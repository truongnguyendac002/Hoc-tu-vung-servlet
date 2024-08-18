<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.ptit.webhoctienganh.model.Vocabulary" %>
<%
    Vocabulary currentWord = (Vocabulary) request.getAttribute("currentWord");
    String topicID = (String) request.getAttribute("topicID");
    Integer currentIndexObj = (Integer) request.getAttribute("currentIndex");
    int currentIndex = currentIndexObj != null ? currentIndexObj : 0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Học Từ Vựng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .vocab-card {
            max-width: 400px;
            margin: 0 auto;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            cursor: pointer;
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
        .vocab-meaning {
            font-size: 20px;
            margin-top: 15px;
            display: none;
        }
        .vocab-phonetic {
            font-size: 18px;
            margin-top: 10px;
            display: none;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Học Từ Vựng</h2>
    <div class="vocab-card" id="vocabCard">
        <img src="<%= currentWord.getImageURL() %>" alt="Hình ảnh từ vựng" class="vocab-image">
        <div class="vocab-word"><%= currentWord.getWord() %></div>
        <div class="vocab-meaning" id="vocabMeaning"><%= currentWord.getMeaning() %></div>
        <div class="vocab-phonetic" id="vocabPhonetic"><%= currentWord.getPronunciation() %></div>
        <button class="btn btn-primary mt-3 w-100" id="flipButton">Hiện nghĩa</button>

        <form method="post" action="learn">
            <input type="hidden" name="topicID" value="<%= topicID %>">
            <input type="hidden" name="index" value="<%= currentIndex %>">
            <button type="submit" class="btn btn-success mt-3 w-100">Tiếp tục</button>
        </form>
    </div>
</div>

<script>
    document.getElementById('flipButton').addEventListener('click', function() {
        var meaning = document.getElementById('vocabMeaning');
        var phonetic = document.getElementById('vocabPhonetic');
        if (meaning.style.display === 'none') {
            meaning.style.display = 'block';
            phonetic.style.display = 'block';
        } else {
            meaning.style.display = 'none';
            phonetic.style.display = 'none';
        }
    });
</script>
</body>
</html>
