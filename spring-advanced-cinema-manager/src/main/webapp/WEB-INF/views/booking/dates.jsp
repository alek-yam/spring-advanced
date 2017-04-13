<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Choose date</title>
</head>
<body>
	<form:form method="post" action="booking/date" modelAttribute="ticketForm">
	
		<h1>Choose date</h1>
	
		<h2>${ticketForm.event.name}:</h2>
	
		<c:forEach var="entry" items="${ticketForm.event.auditoriums}">
		
			<c:set var="label" scope="page">
				<fmt:formatDate value="${entry.key.time}" pattern="yyyy-MM-dd HH:mm" /> (${entry.value})
			</c:set>
			
			<p><form:radiobutton path="time" value="${entry.key.time.time}" label="${label}"/></p>

		</c:forEach>
		
		<p><input type="submit" value="Next"></p>
		
	</form:form>
</body>
</html>