<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	회원정보 입력 결과 페이지
</h1>

<P> 아이디 : ${member.id} </P>
<P> 이름 : ${member.name} </P>
<P> 이메일 : ${member.email} </P>
<P> 주소 : ${member.address} </P>
<P> 휴대폰 번호 : ${member.phoneNumber} </P>
<a href="/lec9">back...</a>
</body>
</html>
