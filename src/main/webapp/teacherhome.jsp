<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ Giáo Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .action-button {
            width: 100%;
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            text-align: center;
            font-size: 20px;
        }
        .logout-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <a href="logout" class="btn btn-danger logout-button">Đăng Xuất</a>
    <h2 class="text-center mb-4">Trang Chủ Giáo Viên</h2>
    <div class="list-group text-center">
        <a href="manage-students" class="btn btn-primary action-button">Quản lý thông tin học viên</a>
        <a href="view-student-progress" class="btn btn-success action-button">Thống kê tiến độ học</a>
    </div>
</div>
</body>
</html>
