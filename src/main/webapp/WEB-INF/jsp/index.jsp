<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>PetClinic</title>
</head>
<body>
<h1>INDEX PAGE</h1>
<form action="logout" method="post">
    <input type="submit" value="logout"/>
    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

</form>

</body>
</html>