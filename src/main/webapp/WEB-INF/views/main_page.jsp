<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>
<br>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отдел</th>
        <th>Зарплата</th>
        <th>  </th>
    </tr>
<c:forEach var = "emp" items="${employeesList}">
    <c:url var="deleteButton" value="/delete">
        <c:param name="empId" value="${emp.id}"/>
    </c:url>
    <c:url var="updateButton" value="/update">
        <c:param name="empId" value="${emp.id}" />
    </c:url>
    <tr>
        <td>${emp.name}</td>
        <td>${emp.surname}</td>
        <td>${emp.department}</td>
        <td>${emp.salary}</td>
        <td>
            <security:authorize access="hasRole('admin')">
            <input type="button" value="Изменить" onclick="window.location.href='${updateButton}'">
            <input type="button" value="Удалить" onclick="window.location.href='${deleteButton}'">
            </security:authorize>
        </td>

    </tr>

</c:forEach>
    <form method="get" action="new">
<security:authorize access="hasAnyRole('admin','HR')">
        <input type="submit" value="Добавить">
</security:authorize>

    </form>
</table>
</body>
</html>
