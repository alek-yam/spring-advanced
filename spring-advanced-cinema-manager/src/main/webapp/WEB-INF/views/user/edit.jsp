<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Edit Profile</title>
</head>
<body>

	<h1>Cinema Manager</h1>
	
	<h2>Edit Profile</h2>
	
	<p style="color:red">${editError}</p>
	
	<form:form method="post" modelAttribute="user" action="users/${user.id}">
	
		<form:input type="hidden" path="id" />

		<label>First Name:</label>
		<form:input type="text" path="firstName" />
		<form:errors path="firstName" />
		<br/>

		<label>Last Name:</label>
		<form:input type="text" path="lastName" />
		<form:errors path="lastName" />
		<br/>

		<label>Birthday:</label>
		<form:input type="text" path="birthday" />
		<form:errors path="birthday" />
		<br/>

		<label>Email:</label>
		<form:input type="text" path="email" />
		<form:errors path="email" />
		<br/>

<!-- 		<label>Password:</label> -->
<%-- 		<form:input type="password" path="password" /> --%>
<%-- 		<form:errors path="password" /> --%>
<!-- 		<br/> -->

		<input type="submit" value="Submit">

	</form:form>

</body>
</html>