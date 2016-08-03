<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>First JSP</title></head>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }
</style>
<body>
<h2>Our Employee List!</h2>

<table>
    <tr>
        <td>Employee ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Technologies</td>
    </tr>
    <tr>
        <br>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><c:out value="${employee.ID}"/></td>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.surname}"/></td>
            <td>
                <c:forEach items="${employee.technologies}" var="technology">
                    <c:out value="${technology.name} "/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>