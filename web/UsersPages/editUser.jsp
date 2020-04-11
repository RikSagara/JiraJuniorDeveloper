
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Редактирование пользователя</title>
</head>
<body>
<form  modelAttribute = "user" action="user" method = "post">
<table>
	<tr>
		<td><b>Логин</b></td>
		<td><input name = "name" value="${form.name}" /></td>
	</tr>
	<tr>
		<td><b>Пароль</b></td>
		<td><input name = "password" value="${form.password}"/></td>
	</tr>
	<tr>
		<td><b>Роль</b></td>
		<td>
			<select name="role" >
				<option value="Admin" >Администратор</option>
				<option value="LoggedUser" selected>Пользователь</option>
			</select>
		</td>
	</tr>
</table>
	<input hidden name = "id" value="${form.id}"/>
	<br/>
	<input type = "submit" value="Изменить"/>
</form>
</body>
</html>
