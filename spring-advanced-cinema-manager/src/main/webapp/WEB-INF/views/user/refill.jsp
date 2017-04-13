<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Refill Balance</title>
</head>
<body>

	<h1>Cinema Manager</h1>
	
	<h2>Refill Balance</h2>
	
	<p style="color:red">${refillError}</p>
	
	<form method="post" action="users/${user.id}/balance">
	
		<label for="balance">Current Balance:</label>
		<input type="text" id="balance" value="${balance}" readonly />
		<br/>

		<label for="sum">Sum of Replenishment:</label>
		<input type="text" id="sum" name="sum" />
		<br/>

		<input type="submit" value="Submit">

	</form>

</body>
</html>