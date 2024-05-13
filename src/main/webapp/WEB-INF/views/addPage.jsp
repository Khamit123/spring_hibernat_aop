<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добаление</title>
</head>
<body>
<form:form method="post" action="/new" modelAttribute="newEmp">

    <form:hidden path="id"/>

    Имя <form:input path="name" />
    <br/>
    <br/>
    Фамилия <form:input path="surname"/>
    <br/>
    <br/>
    Зарплата <form:input path="salary"/>
    <br/>
    <br/>
    Отдел <form:input path="department"/>
    <br/>
    <br/>
    <input type="submit" value="OK">
</form:form>
</body>
</html>
