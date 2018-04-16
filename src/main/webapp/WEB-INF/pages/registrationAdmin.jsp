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
        <form:form action="/admin/newuser" method="POST"  modelAttribute="user">
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
                    <form:radiobutton path="gender" value="FEMALE" label="Female"/>
                    <form:errors path="gender" cssClass="error" /><br>
                    <br></p>

                <label>Birthday</label>
                <form:input type="date" path="birthDate"/>
                <form:errors path="birthDate" cssClass="error" /><br>


                <label>Email address</label>
                <form:input type="text" path="email" required="required"/>
                <form:errors path="email" cssClass="error" />
                <span style="color: red">${uniquemail}</span><br>

                <label>Username</label>
                <form:input type="text" path="username" required="required"/>
                        <form:errors path="username" cssClass="error" />
                <span style="color: red">${uniqueusername}</span><br>
                <br>

                <label>Roles</label>
                <form:select multiple="true" path="roles" items="${roles}" itemLabel="roleName" itemValue="roleName" />
                <%--<form:select path="roles" multiple="">--%>
                    <%--<<option value="ROLE_ADMIN">ADMIN</option>--%>
                    <%--&lt;%&ndash;<input type="radio" value="ROLE_USER">USER<input>&ndash;%&gt;--%>
                    <%--<option value="ROLE_USER" selected>USER</option>--%>
                <%--</form:select>--%>
                <label>Password</label>
                <form:input type="text" path="password" required="required"/>
                        <form:errors path="password" cssClass="error" /><br>
                <label>Repeat password</label>
                <form:input type="text" path="repeatPassword" required="required"/>
                <span style="color: red">${repassword}</span><br>


                <button type="submit">Register new user</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>