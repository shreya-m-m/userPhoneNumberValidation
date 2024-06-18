<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        .container h2 {
            margin-top: 0;
            color: #333;
        }

        .link-container {
            margin: 20px 0;
        }

        .link-container a {
            display: block;
            margin: 10px 0;
            padding: 10px;
            color: #fff;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .link-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome</h2>
        <div class="link-container">
            <a href="registration">User Registration</a>
            <a href="login">User Login</a>
            <a href="admin">Admin Login</a>
        </div>
    </div>
</body>
</html>
