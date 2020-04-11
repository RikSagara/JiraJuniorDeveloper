<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
	<title>Stories</title>
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

	<c:forEach items="${stories}" var="story">
		<tr>
			<td>${story.storyCode}-${story.storyNumber}</td>
			<td>${story.title}</td>
			<td>${story.reporter.name}</td>
			<td>${story.assignee.name}</td>
			<td><c:if test="${verifiedUserName eq story.reporter.name}"><a href="story?id=${story.id}">edit Story</a></c:if></td>
		</tr>
	</c:forEach>

</table>
</body>
</html>
