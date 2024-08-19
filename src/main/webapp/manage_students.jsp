<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="org.ptit.webhoctienganh.model.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Student Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function populateEditForm(studentID, firstName, lastName, phone, dateOfBirth, gender, address, classID) {
            document.getElementById('editStudentID').value = studentID;
            document.getElementById('editFirstName').value = firstName;
            document.getElementById('editLastName').value = lastName;
            document.getElementById('editPhone').value = phone;
            document.getElementById('editDateOfBirth').value = dateOfBirth;
            document.getElementById('editGender').value = gender;
            document.getElementById('editAddress').value = address;
            document.getElementById('editClassID').value = classID;
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Manage Students</h2>
    <a href="teacherhome" class="btn btn-primary">Quay lại</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Date of Birth</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Class ID</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Student student : students) { %>
        <tr>
            <td><%= student.getStudentID() %>
            </td>
            <td><%= student.getFirstName() %>
            </td>
            <td><%= student.getLastName() %>
            </td>
            <td><%= student.getPhone() %>
            </td>
            <td><%= student.getDateOfBirth() %>
            </td>
            <td><%= student.getGender() %>
            </td>
            <td><%= student.getAddress() %>
            </td>
            <td><%= student.getClassID() %>
            </td>
            <td>
                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editStudentModal"
                        onclick="populateEditForm('<%= student.getStudentID() %>', '<%= student.getFirstName() %>', '<%= student.getLastName() %>', '<%= student.getPhone() %>', '<%= student.getDateOfBirth() %>', '<%= student.getGender() %>', '<%= student.getAddress() %>', '<%= student.getClassID() %>')">Edit</button>
                <form action="manage-students" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="studentID" value="<%= student.getStudentID() %>">
                    <button class="btn btn-danger btn-sm">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <!-- Edit Student Modal -->
    <div class="modal fade" id="editStudentModal" tabindex="-1" aria-labelledby="editStudentModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editStudentModalLabel">Edit Student</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="manage-students" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" id="editStudentID" name="studentID">
                        <div class="mb-3">
                            <label for="editFirstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="editFirstName" name="firstName" required>
                        </div>
                        <div class="mb-3">
                            <label for="editLastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="editLastName" name="lastName" required>
                        </div>
                        <div class="mb-3">
                            <label for="editPhone" class="form-label">Phone</label>
                            <input type="text" class="form-control" id="editPhone" name="phone" required>
                        </div>
                        <div class="mb-3">
                            <label for="editDateOfBirth" class="form-label">Date of Birth</label>
                            <input type="date" class="form-control" id="editDateOfBirth" name="dateOfBirth" required>
                        </div>
                        <div class="mb-3">
                            <label for="editGender" class="form-label">Gender</label>
                            <input type="text" class="form-control" id="editGender" name="gender" required>
                        </div>
                        <div class="mb-3">
                            <label for="editAddress" class="form-label">Address</label>
                            <input type="text" class="form-control" id="editAddress" name="address" required>
                        </div>
                        <div class="mb-3">
                            <label for="editClassID" class="form-label">Class ID</label>
                            <input type="text" class="form-control" id="editClassID" name="classID" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%--    <!-- Add Student Form -->--%>
    <%--    <h3 class="mt-5">Add New Student</h3>--%>
    <%--    <form action="manage-students" method="post">--%>
    <%--        <input type="hidden" name="action" value="add">--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="firstName" class="form-label">First Name</label>--%>
    <%--            <input type="text" class="form-control" id="firstName" name="firstName" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="lastName" class="form-label">Last Name</label>--%>
    <%--            <input type="text" class="form-control" id="lastName" name="lastName" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="phone" class="form-label">Phone</label>--%>
    <%--            <input type="text" class="form-control" id="phone" name="phone" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="dateOfBirth" class="form-label">Date of Birth</label>--%>
    <%--            <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="gender" class="form-label">Gender</label>--%>
    <%--            <input type="text" class="form-control" id="gender" name="gender" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="address" class="form-label">Address</label>--%>
    <%--            <input type="text" class="form-control" id="address" name="address" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="accountID" class="form-label">Account ID</label>--%>
    <%--            <input type="text" class="form-control" id="accountID" name="accountID" required>--%>
    <%--        </div>--%>
    <%--        <div class="mb-3">--%>
    <%--            <label for="classID" class="form-label">Class ID</label>--%>
    <%--            <input type="text" class="form-control" id="classID" name="classID" required>--%>
    <%--        </div>--%>
    <%--        <button type="submit" class="btn btn-primary">Add Student</button>--%>
    <%--    </form>--%>
</div>
</body>
</html>
