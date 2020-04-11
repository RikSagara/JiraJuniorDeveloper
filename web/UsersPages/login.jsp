<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
<form modelAttribute = "user" action="login" method = "post" >
    <table>
        <tr>
            <td><b>Логин</b></td>
            <td><input name = "username" /></td>
        </tr>
        <tr>
            <td><b>Пароль</b></td>
            <td><input name = "password" /></td>
        </tr>
    </table>
    <br>
    <input type = "submit" value="Войти"/>
</form>
</body>
</html>
