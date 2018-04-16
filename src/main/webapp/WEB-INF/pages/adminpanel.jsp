<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Admin panel</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        table#t01 {
            width: 100%;
            background-color: #f1f1c1;
        }
    </style>
</head>
<body>
<h1>List of users</h1>
<sec:authorize url="/admin/panel">
    <table>
        <thead>
        <tr>
            <td>id</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Username</td>
            <td>Email</td>
            <td>Date of birth</td>
            <td>Gender</td>
            <td>Password</td>
            <td>Roles</td>
            <td>Action</td>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.birthDate}"/></td>
                <td><c:out value="${user.gender}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td>
                    <c:forEach items="${user.roles}" var="role">
                        <p>${role.roleName}</p>
                    </c:forEach>
                </td>
                <td>
                    <a href="/admin/deleteuser/${user.id}">Delete</a>
                    <a href="/admin/edituser/${user.id}">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/admin/newuser">Add new user</a>

</sec:authorize>
<a href="/logout">Logout</a>
</body>
</html>
