<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Регистрация пользователя</title>
</head>
<body>
<%--@elvariable id="form" type="ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm"--%>
<form:form modelAttribute="form" action="register" method="post" enctype="application/x-www-form-urlencoded">
    <table>
    <tr>
        <td>
            Login:
        </td>
        <td>
            <input type="text" name="userName"  value="${form.userName}"/>
            <form:errors path="userName" cssStyle="color: red" />
        </td>
    </tr>
    <tr>
        <td>
            Password:
        </td>
        <td>
            <input type="password" name="password" value="${form.password}"/>
        </td>
    </tr>
    </table>
    <br/>
    <p>
        <input type="submit">
    </p>
</form:form>
</body>
</html>
