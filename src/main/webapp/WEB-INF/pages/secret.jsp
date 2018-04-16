<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: mbezaliuc
  Date: 10/10/2017
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>SECRET</h1>
<sec:authorize url="/admin">
    This content will only be visible to users who are authorized to send requests to the "/admin" URL.
</sec:authorize>
<a href="/logout">Logout</a>
</body>
</html>
