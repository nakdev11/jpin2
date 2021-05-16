<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント追加（ ${ key_topic }）</title>
<link rel="stylesheet" type="text/css" href="css/keijiban.css" />
</head>
<body>
<h1>${ key_topic } について</h1>
<form action="comment" method="post">
	<a class="button" href="comment?topicId=${ key_topicId }">戻る</a>
	<input class="button" type="submit" value="登録" />
<hr />
	<table>
	<tr>
		<td>名前: </td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>コメント: </td>
		<td><input type="text" name="comment" /></td>
	</tr>

	</table>
</form>
</body>
</html>