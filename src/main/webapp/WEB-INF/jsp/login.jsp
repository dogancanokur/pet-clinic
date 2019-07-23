<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
    Username: <input type="text" name="username"/>
    <br/>
    Password: <input type="text" name="password"/>
    <br/>
    Remember me: <input type="checkbox" name="remember-me"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="login"/>
    <strong style="color: red">
        <c:out value="Login Failed, Incorrect Username or Password"></c:out>
    </strong>
</form>
</body>
</html>
