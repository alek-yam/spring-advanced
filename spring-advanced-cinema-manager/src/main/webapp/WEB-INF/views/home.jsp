<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>Cinema Manager</title>
</head>
<body>

	<sec:authorize var="isAuthenticated" access="isAuthenticated()" />

	<p align="right">
	<c:choose>
		<c:when test = "${isAuthenticated == false}">
			<a href="register">Register</a> &nbsp;
			<a href="login">Login</a>
		</c:when>
		<c:otherwise>
			<sec:authentication var="loggedInUsername" property="principal.username"/>
			Hello ${loggedInUsername}! &nbsp;
			<a href="users/profile">Profile</a> &nbsp;
			<a href="logout">Logout</a>
		</c:otherwise>
	</c:choose>
	</p>
	<hr/>

	<h1>Cinema Manager</h1>

	<h2>Today's events:</h2>
	
	<c:forEach var="event" items="${events}">
		<c:out value="${event.name}"/> &nbsp; <a href="booking/${event.id}">Book tickets</a> <br/>
	</c:forEach>
	
	<h2>Get more infomation:</h2>
	
	<a href="price">View ticket price</a> <br/>
	<a href="tickets">View booked tickets</a> <br/>
	<a href="auditoriums">View auditoriums</a> <br/>
	
	<sec:authorize access="hasRole('BOOKING_MANAGER')">		
		<h2>Administration:</h2>
		
		<a href="admin/users">View users</a> <br/>
	</sec:authorize>

</body>
</html>
