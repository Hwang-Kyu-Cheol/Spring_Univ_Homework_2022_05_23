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
	전체 상품 보기
</h1>

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

<a href="/lec9">Home</a>
</body>
</html>
