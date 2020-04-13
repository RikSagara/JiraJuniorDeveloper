<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Регистрация пользователя</title>
</head>
<body>
<form action="register" method="post" enctype="application/x-www-form-urlencoded">
    <table>
    <tr>
        <td>
            Login:
        </td>
        <td>
            <input type="text" name="name" value="${name}">
        </td>
    </tr>
    <tr>
        <td>
            Password:
        </td>
        <td>
            <input type="password" name="password" value="${password}">
        </td>
    </tr>
    </table>
    <br/>
    <p>
        <input type="submit">
    </p>
</form>
</body>
</html>
