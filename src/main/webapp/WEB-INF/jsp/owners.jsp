<%--
  User: dogancanokur
  Date: 7/14/2019
  Time: 7:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr style="font-weight: bold;" bgcolor="#87cefa">
        <td>ID</td>
        <td>FIRST NAME</td>
        <td>LAST NAME</td>
    </tr>
    </thead>
    <c:forEach items="${owners}" var="owner" varStatus="status">
        <tr bgcolor="${status.index % 2 == 0 ? '#B0FFA3' : '#FFFFFF'}">
            <td>${owner.id}</td>
            <td>${owner.firstName}</td>
            <td>${owner.lastName}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>