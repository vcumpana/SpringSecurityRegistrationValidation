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
    <title>Edit personal data</title>
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
</head>
<body>
<div align="center">
    <div style="width: 300px; height: 500px;">
        <form:form action="/edituser/${user.id}" method="POST"  modelAttribute="user">
            <div class="imgcontainer">
                <img src="../resources/incognito.jpg" alt="Avatar" class="avatar">
            </div>
            <h3 align="center" style="color:maroon;">${message}</h3>

            <div class="container" align="left">
                <label>Name</label>
                <form:input type="text" path="name" required="required"/>
                <form:errors path="name" cssClass="error" /><br>

                <label>Surname</label>
                <form:input type="text" path="surname" required="required"/>
                <form:errors path="surname" cssClass="error" /><br>

                <label>Gender</label>
                <p>
                    <form:radiobutton path="gender" value="MALE" />Male
                    <form:radiobutton path="gender" value="FEMALE" label="Female"/><br></p>

                <label>Birthday</label>
                <form:input type="date" path="birthDate"/>
                <form:errors path="birthDate" cssClass="error" /><br>


                <label>Email address</label>
                <form:input type="text" path="email" required="required"/>
                <form:errors path="email" cssClass="error" /><br>

                <label>Username</label>
                <form:input type="text" path="username" required="required"/>
                        <form:errors path="username" cssClass="error" /><br>

                <label>Password</label>
                <form:input type="text" path="password" required="required"/>
                        <form:errors path="password" cssClass="error" /><br>
                <label>Repeat password</label>
                <form:input type="text" path="repeatPassword" required="required"/>
                        <form:errors path="repeatPassword" cssClass="error" />

                <button type="submit">Save changes</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>