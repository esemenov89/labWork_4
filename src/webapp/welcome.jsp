<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>Internet Library</title>
    <h1>WELCOME!</h1>
</head>
<body>

<% if (request.getAttribute("errorLogin")=="error"){%>
    <p><font color="red">Ошибка: не верно указан логин или пароль</font> </p>
<% } %>

<% String message = (String) request.getAttribute("mess");%>
<h1>
    <% if (message!=null){%>
    <p><font color="red"><%=message%></font> </p>
    <% } %>
</h1>

<% if (request.getSession().getAttribute("userLogin")==null) { %>
<form method="post">
    <input type="text" name="login"/>
    <input type="text" name="password"/>
    <input type="submit" value="login"/>
</form>
<a href="/register">Регистрация</a>
<% } else{ %>

<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout" />
    </form>
</div>

<% if ((Integer)request.getSession().getAttribute("accauntType")==1) {%>
    <a href="/listEntitiesForAdmins">Список произведений и пользователей сайта</a>
<% } else { %>
    <a href="/listEntitiesForUsers">Список произведений</a>
<% }
}%>
</body>
</html>
