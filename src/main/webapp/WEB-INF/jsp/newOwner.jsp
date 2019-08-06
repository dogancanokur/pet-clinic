<%--
  Created by IntelliJ IDEA.
  User: ahmetdogancan.okur
  Date: 06/08/2019
  Time: 16:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Document</title>
</head>
<body>
<form:form modelAttribute="owner" method="post">
    First Name: <form:input path="firstName"></form:input> <br/>
    Last Name: <form:input path="lastName"></form:input> <br/>
    <form:button name="submit">Create</form:button>
</form:form>
</body>
</html>