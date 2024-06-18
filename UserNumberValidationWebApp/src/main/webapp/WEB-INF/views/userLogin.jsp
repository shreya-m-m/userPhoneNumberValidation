<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
	background-color: #f4f4f4;
}

.lcon {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.lcon {
	display: flex;
	flex-direction: column;
}

label {
	margin-bottom: 8px;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 10px;
}

input {
	padding: 8px;
	margin-bottom: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button[type="submit"] {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button[type="submit"]:hover {
	background-color: #45a049;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

	<div class="lcon">
		<% if(request.getAttribute("error") != null) { %>
		<p class="error"><%= request.getAttribute("error") %></p>
		<% } %>
		<h1 style="color: burlywood;">USER LOGIN</h1>

		<form id="loginForm" action="userlogin" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" required><br>
			<br>
			<button type="submit" class="btn" >Login</button>
		</form>
	</div>
	
</body>
</html>