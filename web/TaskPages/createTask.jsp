<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="taskForm" type="ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm" scope="session" />
<html>
<head>
	<title>Создать Task</title>
</head>
<body>
<form action="addTask" method="post" enctype="application/x-www-form-urlencoded">
	<table>
		<tr>
			<td>
				Название
			</td>
			<td>
				<input type="text" name="title"  >
			</td>
		</tr>
		<tr>
			<td>
				Буквенный код
			</td>
			<td>
				<input  name="itemCode" >
			</td>
		</tr>
		<tr>
			<td>
				Цифровой код
			</td>
			<td>
				<input name="itemNumber" >
			</td>
		</tr>
		<tr>
			<td>
				Описание
			</td>
			<td>
				<textarea rows="5" name="description" ></textarea>
			</td>
		</tr>

		<tr>
			<td>
				Получатель
			</td>
			<td>
				<select name="assignee">
					<c:forEach items="${users}" var="user">
						<option value="${user.id}">${user.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<br/>
	<p>
		<input type="submit" value="Создать">
	</p>
</form>
</body>
</html>
