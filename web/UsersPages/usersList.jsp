<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Список пользователей</title>
</head>
<body>
<table >
	<tr>
		<td><b>Имя</b></td>
		<td><b>Пароль</b></td>
		<td><c:if test="${verifiedUserRole eq 'Admin'}"><b>Изменить</b></c:if></td>
	</tr>

	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.name}</td>
			<td>${user.password}</td>
			<td><c:if test="${verifiedUserRole eq 'Admin'}"><a href="user?id=${user.id}">edit User</a></c:if></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>
