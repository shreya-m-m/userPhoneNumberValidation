<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
     
        margin: 0;
        padding: 15px;
      
    }
    .container {
        max-width: 1000px;
        margin: 20px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 12px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    h1, h3 {
        text-align: center;
        color: #333;
    }
    table {
       
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #007bff;
        color: #fff;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
     .delete-button, .home-button {
        background-color: #ffd54f;
        color: #333;
        border: none;
        padding: 10px 20px;
        border-radius: 6px;
        cursor: pointer;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: inline-block;
        margin-right: 10px;
    }
     .delete-button:hover, .home-button:hover {
        background-color: #ffa000; 
    }
    a {
        color: #007bff;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
   <h1 style="color: burlywood;">LIST OF USERS</h1>
    <table border="1">
        <tr>
            <th>User Name </th>
            <th>Number</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.number}</td>
               <td><a href="javascript:void(0)" onclick="confirmDelete(${user.user_id})" class="delete-button">Delete</a></td>
            </tr>
        </c:forEach>
         <a href="/UserNumberValidationWebApp/" class="home-button">HOME</a>
    </table>
   <script>
function confirmDelete(userId) {
  Swal.fire({
    title: 'Are you sure?',
    text: "You won't be able to revert this deletion!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      // User confirmed deletion, proceed with logic
      window.location.href = "deleteUser?user_id=" + userId;
    }
  })
}
</script>
</body>
</html>
