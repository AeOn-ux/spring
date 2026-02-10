<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
	</head>
	<body>
		<h2>게시판</h2>
		<form action="/Board" method="post">
			<input type="text" name="bno" placeholder="게시글 번호"><br>
			<input type="text" name="id" placeholder="작성자"><br>
			<input type="text" name="btitle" placeholder="제목"><br>
			<input type="text" name="bcontent" placeholder="내용"><br>
			
			<input type="submit" value="글쓰기"><br>
		</form>
		<ul>
			<li><a href="/">홈으로</a></li>
		</ul>
	</body>
</html>