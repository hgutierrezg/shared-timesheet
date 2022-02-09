<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="shortcut icon" href="#">
    <!-- Bootstrap JS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<section>
    <h3><small>Enter user credentials</small></h3>
    <hr>
    <form class="form-inline my-2 my-lg-0" action="login" method="POST" name="loginForm">
        <div class="form-group">
            <label class="text" for="username">Username:</label>
            <input type="text" id="username" name="username"
                   class="form-control" placeholder="Username" required/>
        </div>
        <div class="form-group">
            <label class="password-field" for="password">Password:</label>
            <input type="password" id="password" name="password"
                   class="form-control" placeholder="Password" required/>
        </div>
        <button type="submit" class="btn btn-success" ng-disabled="loginForm.$invalid">Login</button>
    </form>
    <br><br>
    <hr>
</section>


</body>
</html>
