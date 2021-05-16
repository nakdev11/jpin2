<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link rel="stylesheet" type="text/css" href="css/keijiban.css" />
</head>
<body>
<h1>${ key_topic } について</h1>
<a class="button" href="topic">戻る</a>
<a class="button" href="comment_register.jsp?topicId=${ key_topicId }">返信</a>
<hr />
<table class="tableArea">
	<tr>
		<th>名前</th>
		<th>日時</th>
		<th>コメント</th>
	</tr>
	<c:forEach items="${ sessionScope.filtered_comments }" var="cmt">
	<tr>
		<td><c:out value="${ cmt.name }"></c:out></td>
		<td><fmt:formatDate value="${ cmt.date }" pattern="M/dd HH:mm:ss" /></td>
		<td><c:out value="${ cmt.comment }" /></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>