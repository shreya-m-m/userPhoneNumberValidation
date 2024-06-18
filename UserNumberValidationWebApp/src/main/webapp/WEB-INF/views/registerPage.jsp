<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0px;
             background-color: #363636;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #666;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer; 
            transition: background-color 0.3s ease;
        }
        button[type="submit"]:hover {
            background-color: #0056b3;
        }
       .popup {
    width: 400px;
    background: #fff;
    border-radius: 6px;
    position: fixed;
    top: 0%;
    left: 50%;
    transform: translate(-50%, -50%) scale(0.1);
    text-align: center;
    padding: 0 30px 30px; 
    color: #333;
    visibility: hidden;
    transition: transform 0.8s, top 0.8s;

    
}
	.open-popup{
		visibility: visible;
		top: 50%;
		transform: translate(-50%, -50%) scale(1);
	
	}
        .popup h2{
        font-size: 38px;
        font-weight: 500;
        margin: 30px 0 10px;
        }
        .popup button{
        	width:100px;
        	margin-top: 50px;
        	padding: 10px 0;
        	background: #6fd649;
        	color: #fff;
        	border; 0;
        	outline: none;
        	font-size: 18px;
        	border-radius: 4px;
        	cursor: pointer;
        	box-shadow: 0 5px 5px rgba(0,0,0,0.2);
        
        }
</style>

</head>
<body>
	<h2 style="color: burlywood;">USER REGISTRATION</h2>
	<form action="register" method="post">
		<label for="username">Username:</label> <input type="text"
			id="username" name="username" required
			aria-labelledby="usernameError"><br>
		<br> <label for="password">Password:</label> <input
			type="password" id="password" name="password" required
			aria-labelledby="passwordError"> <span id="passwordError"
			style="color: red;" aria-live="polite"></span><br>
		<br> <label for="number">Phone Number:</label> <input type="text"
			id="number" name="number" required aria-labelledby="numberError">
		<span id="numberError" style="color: red;" aria-live="polite"></span><br>
		<br>
		<button type="submit" class="btn" id="submitButton" disabled >Register</button>
	</form>

	  <div class="popup" id="popup">
        <h2>Success!</h2>
        <p>You have registered successfully.</p>
        <button onclick="closePopup()">Close</button>
    </div>

	<script>
	
        document.getElementById("password").addEventListener("input", function() {
            var passwordInput = document.getElementById("password");
            var passwordError = document.getElementById("passwordError");
            var password = passwordInput.value;
            var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;

            if (!passwordPattern.test(password)) {
                passwordError.textContent = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long.";
                submitButton.disabled = true;
            } else {
                passwordError.textContent = "";
                submitButton.disabled = false;
            }
        });

        document.getElementById("number").addEventListener("input", function() {
            var numberInput = document.getElementById("number");
            var numberError = document.getElementById("numberError");
            var number = numberInput.value;
            var numberPattern = /^\d{12}$/;

            if (!numberPattern.test(number) || allDigitsSame(number)) {
                numberError.textContent = "Invalid number. Must be a 12-digit telephone number with no special characters or letters, and all digits should not be the same.";
                submitButton.disabled = true;
            } else {
                numberError.textContent = "";
                submitButton.disabled = false;
            }
        });

        function allDigitsSame(number) {
            var firstDigit = number.charAt(0);
            for (var i = 1; i < number.length; i++) {
                if (number.charAt(i) !== firstDigit) {
                    return false;
                }
            }
            return true;
        }
    </script>
</body>
</html>
