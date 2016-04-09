<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<title>My Area On Bluerootser</title>

</head>
<body >
<h1>Your Account Information</h1>

<form class="form-signin" action="/bluerootser/update" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<label for="inputEmail" class="sr-only">Email address</label>
	<input type="text" name="email" id="inputEmail" class="form-control" value="${curUser.email}" required autofocus>
	
	<label for="inputPassword" class="sr-only">Password</label>
	<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
	
	<label for="inputDob" class="sr-only">Birthday</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required>
	
	<label for="inputFirstName" class="sr-only">First Name</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required>
	
	<label for="inputLastName" class="sr-only">Last Name</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required>
	
	<button class="btn btn-lg btn-primary btn-block" id="update" type="submit">Update</button>
</form>
</body>
</html>