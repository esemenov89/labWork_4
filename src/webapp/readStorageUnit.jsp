<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<html>
<head>
    <title>List of books</title>
</head>
<body>

<div style="position: fixed; top: 0%; right: 0;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout" />
    </form>
</div>
<% String title = (String) request.getAttribute("title");%>
<h1>
    <% if (title!=null){%>
        <%=title%>
    <% } %>
</h1>
<table>
    <% String text = (String) request.getAttribute("text");%>
        <% if (text!=null){%>
        <tr>
            <td><%=text%></td>
        </tr>
    <% } %>
</table>
</body>
</html>
