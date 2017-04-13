<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<title>Login</title>
</head>
<body>

	<h1>Cinema Manager</h1>

	<h2>Login:</h2>

	<form action='login/perform' method="post">
	
		<c:if test="${param.error != null}">        
        	<p style="color:red">Invalid username or password.</p>
    	</c:if>

		<label>Email:</label>
		<input type="text" id="email" name="email" placeholder="email"/>
		<br/>

		<label>Password:</label>
		<input type="password" id="password" name="password" placeholder="password"/>
		<br/>
		
		<label>Remember Me?</label>
		<input type="checkbox" id="remembe-me" name="remembe-me" value="true" />
		<br/>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

		<button id="loginButton">Login</button>
		
	</form>

</body>
</html>