<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDo管理 タスク一覧</title>
<link rel="stylesheet" href="css/todo.css" />
</head>
<body>
<h1>ToDo管理 タスク一覧（DB保存 MyBatis版）</h1>
<form action="list" method="get">
	<select name="searchCondition">
		<option <c:if test="${ searchCondition == '未済' }">selected="selected"</c:if>>未済</option>
		<option <c:if test="${ searchCondition == '済み' }">selected="selected"</c:if>>済み</option>
		<option <c:if test="${ searchCondition == '全て' }">selected="selected"</c:if>>全て</option>
	</select>
	<input class="button" type="submit" value="検索" />
	<a class="button" href="register">登録</a>
</form>
<hr />
<form action="list" method="post">
	<table class="tableArea">
		<tr>
			<th>選択</th>
			<th>タスク内容</th>
			<th>期日</th>
			<th>担当</th>
			<th>状況</th>
			<th>編集</th>
		</tr>
		<c:forEach items="${ tasks }" var="task" varStatus="status">
			<c:if test="${ task.taskStatus == searchCondition || searchCondition == '全て' }">
				<tr>
					<td><input type="checkbox" value="${ task.taskId }" name="taskIds_finished" /></td>
					<td><c:out value="${ task.content }"></c:out></td>
					<td><fmt:formatDate value="${ task.deadline }" pattern="yyyy/MM/dd"></fmt:formatDate></td>
					<td><c:out value="${ task.assignee }"></c:out></td>
					<td><c:out value="${ task.taskStatus }"></c:out></td>
					<td><a class="button" href="update?taskId=${ task.taskId }">修正</a> <a class="button" href="delete?taskId=${ task.taskId }">削除</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<input class="button" type="submit" value="一括完了" />
</form>
</body>
</html>