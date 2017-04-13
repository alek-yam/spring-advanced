<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>User Profile</title>
</head>
<body>

	<h1>Cinema Manager</h1>

	<h2>User Profile</h2>
	
	ID: ${user.id} <br/>
	Name: ${user.fullName} <br/>
	Birthday: <fmt:formatDate value="${user.birthday.time}" pattern="yyyy-MM-dd" /> <br/>
	Email: ${user.email} <br/>
	Balance: ${balance} <br/>

	<sec:authorize var="isAuthenticated" access="isAuthenticated()" />

	<p>
	<c:choose>
		<c:when test = "${isAuthenticated == false}">
			<a href="login">Login</a>
		</c:when>
		<c:otherwise>
			<a href="users/${user.id}/edit">Edit Profile</a> <br/>
			<a href="users/${user.id}/changepass">Change Password</a> <br/>
			<a href="users/${user.id}/refillbalance">Refill Balance</a>
		</c:otherwise>
	</c:choose>
	</p>

</body>
</html>