<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDo管理 タスク削除</title>
<link rel="stylesheet" href="css/todo.css" />
</head>
<body>
<h1>ToDo管理 タスク削除</h1>
<h3>タスクを削除します。よろしいですか？</h3>
<form action="delete" method="post">
	<table>
		<tr>
			<th>タスク内容: </th>
			<td><c:out value="${ task.content }"></c:out></td>
		</tr>
		<tr>
			<th>期日: </th>
			<td><fmt:formatDate value="${ task.deadline }" pattern="yyyyMMdd"></fmt:formatDate></td>
		</tr>
		<tr>
			<th>担当: </th>
			<td><c:out value="${ task.assignee }"></c:out></td>
		</tr>
		<tr>
			<th>状況: </th>
			<td><c:out value="${ task.taskStatus }"></c:out></td>
		</tr>
	</table>
	<a class="button" href="list?searchCondition=${ searchCondition }">戻る</a>
	<input class="button" type="submit" value="削除" />
</form>
</body>
</html>