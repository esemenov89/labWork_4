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

            <h1>
            Список произведений:
            </h1>

        <script>
        function getdetails(obj) {
            var o1 = document.getElementsByName("readIsn");
            var o2 = document.getElementsByName("changeIsn");
            var o3 = document.getElementsByName("delIsn");
            val1=o1[0];
            val2=o2[0];
            val3=o3[0];
        for (var i = 0; i < o1.length; i++) {
            if (o1[i].value == obj.name) {
                var val1 = o1[i];
            }
            if (o2[i].value == obj.name) {
                var val2 = o2[i];
            }
            if (o3[i].value == obj.name) {
                var val3 = o3[i];
            }
        }
        if(obj.id==1){
            val1.name="readIsn";
            val2.name="";
            val3.name="";
        }
        if(obj.id==2){
            val1.name="";
            val2.name="changeIsn";
            val3.name="";
        }
        if(obj.id==3){
            val1.name="";
            val2.name="";
            result = confirm("Вы уверены, что хотите удалить эту книгу из каталога?");
            if(result){
                val3.name="delIsn";
            }
            else {
                val3.name="";
            }
        }
    }
</script>
<table>
    <c:forEach items="${requestScope.books}" var="book">
        <tr>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td>
            <td><c:out value="${book.publishingHouse}"></c:out></td>
            <td><c:out value="${book.city}"></c:out></td>
            <td><c:out value="${book.year}"></c:out></td>
            <td><c:out value="${book.pagesCount}"></c:out></td>
            <td><form method="post">
                <input type="submit" value="read" id="1" name="${book.isn}" onClick = "getdetails(this)"/>
                <input type="submit" value="change" id="2" name="${book.isn}" onClick = "getdetails(this)"/>
                <input type="submit" value="del" id="3" name="${book.isn}" onClick = "getdetails(this)"/>
                <input type="hidden" name="readIsn" value="${book.isn}"/>
                <input type="hidden" name="changeIsn" value="${book.isn}"/>
                <input type="hidden" name="delIsn" value="${book.isn}"/>
            </form></td>
        </tr>
    </c:forEach>
</table>
<p></p>
<h1>
    Список пользователей:
</h1>
<table>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.accountType}"></c:out></td>
            <td><c:out value="${user.locked}"></c:out></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
