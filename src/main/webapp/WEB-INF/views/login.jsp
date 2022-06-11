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
	로그인
</h1>

<% request.setCharacterEncoding("UTF-8"); %>
<form:form action="/lec9/login" modelAttribute="loginForm">
	<p> <label> <spring:message code="userId" />:<br>
	<form:input path="userId" /> <form:errors path="userId" /> </label> </p>
	<br> <spring:message code="rememberid" />
	<form:checkbox path="rememberid" /> </p>
	<p> <label> <spring:message code="pwd" />:<br>
	<form:input path="pwd" /> <form:errors path="pwd" /> </label> </p>
	<button class="btn" type="submit"> 제출 </button>
</form:form>
</body>
</html>
