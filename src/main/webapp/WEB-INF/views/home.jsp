<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>

<h1>
	Hwang's 쇼핑몰에 오신 것을 환영합니다.
</h1>

<c:if test="${empty userName}">
<P>1. <a href="/lec9/signup">회원가입</a></P>
<P>2. <a href="/lec9/login">로그인</a></P>
<P>3. <a href="/lec9/item">전체 상품 보기</a></P>
<P>4. <a href="/lec9/item/search">상품 검색</a></P>
</c:if>

<c:if test="${! empty userName}">
<h3>
	${userName} 님, 환영합니다.
</h3>
<P>1. <a href="/lec9/mypage/${userId}">회원정보 조회</a></P>
<P>2. <a href="/lec9/modify/${userId}">회원정보 변경</a></P>
<P>3. <a href="/lec9/logout">로그아웃</a></P>
<P>4. <a href="/lec9/withdraw">회원탈퇴</a></P>
<P>5. <a href="/lec9/order/${userId}">주문 내역 조회</a></P>
<P>6. <a href="/lec9/item">전체 상품 보기</a></P>
<P>7. <a href="/lec9/item/search">상품 검색</a></P>
</c:if>

</body>
</html>
