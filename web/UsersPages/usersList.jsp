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
		<td><c:if test="${verifiedUserRole eq 'Admin'}"><b>Пароль</b></c:if></td>
		<td><c:if test="${verifiedUserRole eq 'Admin'}"><b>Изменить</b></c:if></td>
	</tr>

	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.userName}</td>
			<td><c:if test="${verifiedUserRole eq 'Admin'}">${user.password}</c:if></td>
			<td><c:if test="${verifiedUserRole eq 'Admin'}"><a href="user?id=${user.id}">edit User</a></c:if></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>
