<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板2</title>
<link rel="stylesheet" href="css/keijiban.css" />
</head>
<body>
<h1>掲示板2 DB保存版（JDBC）</h1>
<a class="button" href="topic_register.jsp">トピック登録</a>
<hr />
<table class="tableArea">
	<tr>
		<th>トピック</th>
		<th>名前</th>
		<th>日時</th>
		<th>参照/返信</th>
	</tr>
	<c:forEach items="${ sessionScope.topicList }" var="topic">
		<tr>
			<td><c:out value="${ topic.content }"></c:out></td>
			<td><c:out value="${ topic.name }"></c:out></td>
			<td><fmt:formatDate value="${ topic.date }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
			<td><a class="button" href="comment?topicId=${ topic.topicId }&topicContent=${ topic.content }">参照/返信</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>