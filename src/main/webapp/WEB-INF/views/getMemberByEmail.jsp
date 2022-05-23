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
	이메일로 회원 조회하기
</h1>

<% request.setCharacterEncoding("UTF-8"); %>
<form:form action="/lec9/getMember/email/submit" modelAttribute="emailInput">
	<p> <label> <spring:message code="email" />:<br>
	<form:input path="email" /> <form:errors path="email" /> </label> </p>
	<br><br>
	<button class="btn" type="submit"> 제출 </button>
</form:form>
</body>
</html>
