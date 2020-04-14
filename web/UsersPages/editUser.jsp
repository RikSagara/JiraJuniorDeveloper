
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Редактирование пользователя</title>
</head>
<body>
<%--@elvariable id="form" type="ru.ngu.JiraJuniorDeveloper.Web.Forms.UserForm"--%>
<form:form  modelAttribute = "form" action="user" method = "post">
<table>
	<tr>
		<td><b>Логин</b></td>
		<td><input name = "userName" value="${form.userName}" /></td>
	</tr>
	<tr>
		<td><b>Пароль</b></td>
		<td><input name = "password" value="${form.password}"/></td>
	</tr>
	<tr>
		<td><b>Роль</b></td>
		<td>
			<form:select path="role">

				<option value="Admin">Администратор</option>
				<option value="LoggedUser">Пользователь</option>
			</form:select>
		</td>
	</tr>
</table>
	<input hidden name = "userId" value="${form.id}"/>
	<br/>
	<input type = "submit" value="Изменить"/>
</form:form>
</body>
</html>
