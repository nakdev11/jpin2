<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDo管理 タスク登録</title>
<link rel="stylesheet" href="css/todo.css" />
</head>
<body>
<h1>ToDo管理 タスク登録</h1>
<form action="register" method="post">
	<table>
		<tr>
			<th>タスク内容: </th>
			<td><input type="text" name="content" /></td>
		</tr>
		<tr>
			<th>期日: </th>
			<td><input type="date" name="deadline" /></td>
		</tr>
		<tr>
			<th>担当: </th>
			<td><input type="text" name="assignee" /></td>
		</tr>
		<tr>
			<th>状況: </th>
			<td>
				<select name="taskStatus">
					<option selected="selected">未済</option>
					<option>済み</option>
				</select>
			</td>
		</tr>
	</table>
	<a class="button" href="list?searchCondition=${ searchCondition }">戻る</a>
	<input class="button" type="submit" value="登録" />
</form>
</body>
</html>