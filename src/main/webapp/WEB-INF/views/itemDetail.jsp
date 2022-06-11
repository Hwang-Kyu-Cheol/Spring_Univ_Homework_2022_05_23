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
	상품 이름 : ${item.name}
</h1>

<P> 가격 : ${item.price} </P>
<P> 상세 정보 : ${item.description} </P>
<c:if test="${! empty userId}">
<form method="post" action="/lec9/order">
<input type="hidden" name="itemId" value=${ item.id }>
<button type="submit">주문하기</button>
</form>
</c:if>
<a href="/lec9/item">전체 상품 보기</a>
</body>
</html>
