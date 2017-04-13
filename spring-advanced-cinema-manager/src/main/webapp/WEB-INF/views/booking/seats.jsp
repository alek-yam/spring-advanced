<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Choose seats</title>
</head>
<body>
	<form:form method="post" action="booking/seats" modelAttribute="ticketForm">
	
		<h1>Choose seats</h1>
	
		<h2>${ticketForm.auditoriumName}:</h2>
		
		<form:checkboxes path="seats" items="${ticketForm.allSeats}" delimiter="<br/>"/>
		
		<p><input type="submit" value="Next"></p>
		
	</form:form>
</body>
</html>