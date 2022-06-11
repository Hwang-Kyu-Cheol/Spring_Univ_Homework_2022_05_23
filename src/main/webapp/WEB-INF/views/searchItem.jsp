<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	상품 검색
</h1>

<% request.setCharacterEncoding("UTF-8"); %>
<form action="/lec9/item/search" method="get">
	<input type="text" name="name" placeholder="검색할 상품 이름을 입력하세요."/>
	<button type="submit"> 검색 </button>
</form>

<c:if test="${! empty itemList}">
<h5> 검색 결과 </h5>
<table border="1">
	<th>순서</th>
	<th>상품 이름</th>
	
	<c:forEach items="${ itemList }" var="item">
	
	<tr>
		<td>${ item.id }</td>
		<td><a href="/lec9/item/${ item.id }">${ item.name }</a></td>
	</tr>

	</c:forEach>
</table>
</c:if>
<a href="/lec9">Home</a>
</body>
</html>
