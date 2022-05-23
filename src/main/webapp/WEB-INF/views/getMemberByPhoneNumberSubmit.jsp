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
	회원정보 조회 결과 페이지
</h1>

<P> 아이디 : ${id} </P>
<P> 이름 : ${name} </P>
<P> 이메일 : ${email} </P>
<P> 주소 : ${address} </P>
<P> 휴대폰 번호 : ${phoneNumber} </P>
<a href="/lec9">back...</a>
</body>
</html>
