<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="data" type="ru.ngu.JiraJuniorDeveloper.Web.Forms.UserListForm" scope="request" />
<html>
<head>
	<title>Создать Task</title>
</head>
<body>
<%--@elvariable id="form" type="ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm"--%>
<form:form action="addTask" method="post" modelAttribute="form" enctype="application/x-www-form-urlencoded">
	<table>
		<tr>
			<td>
				Название
			</td>
			<td>
				<input name="title"  value="${form.title}">
			</td>
		</tr>
		<tr>
			<td>
				Буквенный код
			</td>
			<td>
				<input name="itemCode" value="${form.itemCode}" >
				<form:errors path="itemCode" cssStyle="color: #ff0000" />
			</td>
		</tr>
		<tr>
			<td>
				Цифровой код
			</td>
			<td>
				<input name="itemNumber" value="${form.itemNumber}">
				<form:errors path="itemNumber" cssStyle="color: #ff0000" />
			</td>
		</tr>
		<tr>
			<td>
				Описание
			</td>
			<td>
				<textarea rows="5" name="description" content="${form.description}"></textarea>
			</td>
		</tr>

		<tr>
			<td>
				Получатель
			</td>
			<td>
				<select name="assignee">

					<c:forEach items="${data.users}" var="user">
						<option value="${user.id}">${user.userName}</option>
					</c:forEach>

				</select>
			</td>
		</tr>
	</table>
	<br/>
	<p>
		<input type="submit" value="Создать">
	</p>
</form:form>
</body>
</html>
