<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	아이디로 회원 조회하기
</h1>

<% request.setCharacterEncoding("UTF-8"); %>
<form:form action="/lec9/getMember/id/submit" modelAttribute="idInput">
	<p> <label> <spring:message code="id" />:<br>
	<form:input path="id" /> <form:errors path="id" /> </label> </p>
	<br><br>
	<button class="btn" type="submit"> 제출 </button>
</form:form>
</body>
</html>
