<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: victor
 Date: 4/5/18
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
</head>
<body>
<div align="center">
    <div style="width: 300px; height: 500px;">
        <form:form method="POST" action="/registration">
            <div class="imgcontainer">
                <img src="../resources/incognito.jpg" alt="Avatar" class="avatar">
            </div>
            <h3 align="center" style="color:maroon;">${message}</h3>

            <div class="container" align="left">
                <label>Username</label>
                <input type="text" name="username" required="required"/>

                <label>Password</label>
                <input type="password" name="password" required="required"/>

                <label>Gender</label>
                <p>
                    <input type="radio" name="gender" value="MALE" checked>Male
                    <input type="radio" name="gender" value="FEMALE">Female<br></p>
                <button type="submit">Register</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>