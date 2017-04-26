<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>Register in Internet Library</title>
    <h1>Регистрация</h1>
</head>
<body>
<form method="post">
    <p>Введите логин:</p>
    <% if (request.getAttribute("registerLogin")!=null) { %>
    <p><font color="red"><%= request.getAttribute("registerLogin") %></font></p>
    <% } %>
    <input type="text" name="login"/>
    <p>Введите пароль (пароль должен содержать латинские символы и цифры, минимум 8 символов):</p>
    <% if (request.getAttribute("registerPassword")!=null) { %>
    <p><font color="red"><%= request.getAttribute("registerPassword") %></font> </p>
    <% } %>
    <input type="text" name="password"/>
    <p>Введите email:</p>
    <% if (request.getAttribute("registerMail")!=null) { %>
    <p><font color="red"><%= request.getAttribute("registerMail") %></font></p>
    <% } %>
    <input type="text" name="email"/>
    <p></p>
    <input type="submit" value="ok"/>
</form>
</body>
</html>
