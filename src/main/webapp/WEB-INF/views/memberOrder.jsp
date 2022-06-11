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
	주문 내역
</h1>

<c:if test="${ empty orderList }">
<P>주문 내역이 존재하지 않습니다.</P>
</c:if>

<c:if test="${! empty orderList}">
<table border="1">
	<th>순서</th>
	<th>상품 이름</th>
	<th>주문 날짜</th>
	
	<%
	int i = 1;
	%>
	<c:forEach items="${ orderList }" var="order">
	<tr>
		<td><%= i%></td>
		<td>${ order.item.name }</td>
		<td>${ order.orderDate }</td>
	</tr>
	<%
	i++;
	%>
	</c:forEach>
</table>
</c:if>

<a href="/lec9">Home</a>
</body>
</html>
