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
	${userName} 님 정보 수정
</h1>
<% request.setCharacterEncoding("UTF-8"); %>
<form:form action="/lec9/modify" modelAttribute="member">
	<p> <label> <spring:message code="id" />:<br>
	<form:input path="id" /> <form:errors path="id" /> </label> </p>
	<p> <label> <spring:message code="password" />:<br>
	<form:input path="password" /> <form:errors path="password" /> </label> </p>
	<p> <label> <spring:message code="name" />:<br>
	<form:input path="name" /> <form:errors path="name" /> </label> </p>
	<p> <label> <spring:message code="email" />:<br>
	<form:input path="email" /> <form:errors path="email" /> </label> </p>
	<p> <label> <spring:message code="address" />:<br>
	<form:input path="address" /> <form:errors path="address" /> </label> </p>
	<p> <label> <spring:message code="phoneNumber" />:<br>
	<form:input path="phoneNumber" /> <form:errors path="phoneNumber" /> </label> </p>
	<p> <label> <spring:message code="birthday" />:<br>
	<form:input path="birthday" /> <form:errors path="birthday" /> </label> </p>
	<br><br>
	<button class="btn" type="submit"> 제출 </button>
</form:form>
</body>
</html>
