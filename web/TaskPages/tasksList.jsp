<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Tasks</title>
</head>
<body>
<table >
	<tr>
		<td><b>Код</b></td>
		<td><b>Название</b></td>
		<td><b>Создатель</b></td>
		<td><b>Исполнитель</b></td>
		<td><b>Изменить</b></td>
	</tr>

	<c:forEach items="${tasks}" var="task">
		<tr>
			<td>${task.taskCode}-${task.taskNumber}</td>
			<td>${task.title}</td>
			<td>${task.reporter.userName}</td>
			<td>${task.assignee.userName}</td>
			<td><c:if test="${verifiedUserName eq task.reporter.userName}"><a href="task?id=${task.id}">edit Task</a></c:if></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>
