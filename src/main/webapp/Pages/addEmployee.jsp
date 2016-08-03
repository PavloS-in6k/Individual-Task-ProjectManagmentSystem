<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add employee</title>
</head>
<body>
Wanna add an employee, huh? <BR>
Let's try. <BR>
<BR>
<form action="<c:url value="/addEmployee"/>" method="POST">
    Put here employee name <input type="text" name="employeeName"><BR>
    And here - employee surname <input type="TEXT" name="employeeSurname"><BR>
    How about some technologies?
    <select name="technologiesSelect" size="3" multiple="multiple" tabindex="1">
        <c:forEach items="${technologies}" var="technology">
            <option>
                <c:out value="${technology.name}"/>
            </option>
        </c:forEach>
    </select>

    <input type="submit" name="submit" value="submit"/>
</form>
</body>
</html>
