<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/keijiban.css" />
</head>
<body>
<h1>トピック登録</h1>

<form action="topic" method="post">
	<a class="button" href="topic">戻る</a>
	<input class="button" type="submit" value="登録" />
	<hr />
	<table>
		<tr>
			<td>トピック: </td>
			<td><input type="text" name="content" /></td>
		</tr>
		<tr>
			<td>名前: </td>
			<td><input type="text" name="name" /></td>
		</tr>
	</table>
</form>
</body>
</html>