<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 완료</title>
	</head>
	<body>
		<h2>회원가입 완료</h2>
		<h3>아이디 : ${member.id}</h3>
		<h3>비밀번호 : ${member.pw}</h3>
		<h3>이름 : ${member.name}</h3>
		<h3>이메일 : ${member.email}</h3>
		<h3>번화번호 : ${member.phone}</h3>
		<h3>성별 : ${member.gender}</h3>
		<h3>취미 : ${member.hobby}</h3>
		
		<ul>
			<li><a href="/">홈으로</a></li>
		</ul>
	</body>
</html>