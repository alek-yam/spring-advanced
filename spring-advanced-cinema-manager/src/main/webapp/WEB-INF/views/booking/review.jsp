<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Confirmation</title>
</head>
<body>
	<form:form method="get" action="booking/perform" modelAttribute="ticketForm">
	
		<h1>Confirmation</h1>
	
		<h2>Order details:</h2>
		
		<table>
			<tr>
				<td>Event:</td>
				<td>${ticketForm.event.name}</td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><fmt:formatDate value="${ticketForm.date.time}" pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
			<tr>
				<td>Auditorium:</td>
				<td>${ticketForm.auditoriumName}</td>
			</tr>
			<tr>
				<td>Seats:</td>
				<td>${ticketForm.seats}</td>
			</tr>
			<tr>
				<td>Total price:</td>
				<td>${priceReport.totalPrice}</td>
			</tr>
			<tr>
				<td>Discount:</td>
				<td>${priceReport.discount}</td>
			</tr>
			<tr>
				<td>Final price:</td>
				<td>${priceReport.finalPrice}</td>
			</tr>
		</table>
		
		<p><input type="submit" value="Order"></p>
		
	</form:form>
</body>
</html>