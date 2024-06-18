<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            width: 100%;
        }
        label {
            margin-bottom: 5px;
            color: #666;
        }
        input[type="text"] {
            width: calc(100% - 12px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .home-button {
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
        a {
            color: #007bff;
            text-decoration: none;
        }
       .success-message {
   
    color: green; /* Changed to green as per your request */
    margin-bottom: 10px; /* Adjusted margin-bottom */
    font-size: 50px; /* Increased font size */
    text-align: center;
}

    </style>
</head>
<body>
<%-- Display success message if available --%>
       <c:if test="${not empty successMessage}">
            <div class="success-message">
                ${successMessage}
            </div>
        </c:if>
    <div class="container">
        <h2>Edit Number</h2>
            
        <form id="updateForm" action="updateNumber" method="post">
            <label>ID:</label>
            <span>${user.user_id}</span><br><br>
            <input type="hidden" name="user_id" value="${user.user_id}">
            
            <label for="username">Username:</label>
            <span>${user.username}</span><br><br>
            
            <label for="number">New Number:</label>
            <input type="text" id="number" name="number" value="${user.number}"><br>
            <span id="numberError" style="color: red;"></span><br>
        
            <button type="submit" class="btn" id="updateButton" disabled >Update</button>
        </form><br>
        
        <a href="/UserNumberValidationWebApp/" class="home-button">HOME</a>
        
    </div>
    
    <script type="text/javascript">
        document.getElementById("number").addEventListener("input", function() {
            var numberInput = document.getElementById("number");
            var numberError = document.getElementById("numberError");
            var submitButton = document.getElementById("updateButton");

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
