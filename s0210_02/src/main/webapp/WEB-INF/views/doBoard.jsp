<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글쓰기 완료</title>
	</head>
	<body>
		<h2>글쓰기 완료</h2>
		<h3>아이디 : ${board.id}</h3>
		<h3>글번호 : ${board.bno}</h3>
		<h3>제목 : ${board.btitle}</h3>
		<h3>내용 : ${board.bcontent}</h3>
		
		<ul>
			<li><a href="/">홈으로</a></li>
		</ul>
	</body>
</html>