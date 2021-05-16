<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<link rel="stylesheet" href="css/keijiban.css" />
</head>
<body>
<h1>掲示板 session保存版</h1>
<a class="button" href="topic_register.jsp">トピック登録</a>
<hr />
<table class="tableArea">
	<tr>
		<th>トピック</th>
		<th>名前</th>
		<th>日時</th>
		<th>コメント</th>
		<th>参照/返信</th>
	</tr>
	<c:forEach items="${ sessionScope.comments }" var="cmt">
		<c:if test="${ cmt.rootFlg }">
			<tr>
				<td><c:out value="${ cmt.topic }"></c:out></td>
				<td><c:out value="${ cmt.name }"></c:out></td>
				<td><fmt:formatDate value="${ cmt.date }" pattern="M/dd HH:mm:ss" /></td>
				<td><c:out value="${ cmt.comment }" /></td>
				<td><a class="button" href="comment?topicId=${ cmt.topicId }">参照/返信</a></td>
			</tr>
		</c:if>
	</c:forEach>
</table>
</body>
</html>