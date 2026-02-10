<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
	</head>
	<body>
		<h2>회원정보수정</h2>
		<form action="/dojoin" method="post">
			<input type="text" name="id" value="${member.id}" placeholder="아이디를 입력하세요."><br>
			<input type="text" name="pw" value="${member.pw}" placeholder="비밀번호를 입력하세요."><br>
			<input type="text" name="name" value="${member.name}" placeholder="이름을 입력하세요."><br>
			<input type="text" name="phone" value="${member.phone}" placeholder="전화번호를 입력하세요."><br>
			<input type="text" name="email" value="${member.email}" placeholder="이메일을 입력하세요."><br>
			<label>성별</label><br>
			<input type="radio" id="male" name="gender" value="남자" >
			<label for="male">남자</label>
			<input type="radio" id="female" name="gender" value="여자"
			<c:if test="${fn:contains(member.gender, '남자')}">checked</c:if>>
			<label for="female">여자</label>
			<br>
			<label>취미</label><br>
			<input type="checkbox" id="game" name="hobby" value="게임"
			<c:if test="${fn:contains(member.hobby, '게임')}">checked</c:if>>
			<label for="game">게임</label>
			<input type="checkbox" id="walk" name="hobby" value="산책"
			<c:if test="${fn:contains(member.hobby, '산책')}">checked</c:if>>
			<label for="walk">산책</label>
			<input type="checkbox" id="swim" name="hobby" value="수영">
			<label for="swim">수영</label>
			<input type="checkbox" id="book" name="hobby" value="독서">
			<label for="book">독서</label>
			<input type="checkbox" id="golf" name="hobby" value="골프">
			<label for="golf">골프</label><br>
			<input type="submit" value="회원가입"><br>
		</form>
		<ul>
			<li><a href="/">홈으로</a></li>
		</ul>
	</body>
</html>