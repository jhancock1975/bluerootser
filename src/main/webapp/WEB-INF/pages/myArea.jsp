<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<link href="/bluerootser/resources/css/signin.css" rel="stylesheet">
<title>My Area On Bluerootser</title>

</head>
<body >
<div class="container">
<h2 class="form-signin-heading">Your Account Information</h2>
<form class="form-signin" action="/bluerootser/update" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
	<label for="inputEmail" class="form-control">Email address</label>
	<input type="text" name="email" id="inputEmail" class="form-control" value="${curUser.email}" required autofocus/>
	
	<label for="inputPassword" class="form-control">Password</label>
	<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required/>
	
	<label for="inputDob" class="form-control">Birthday</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required/>
	
	<label for="inputFirstName" class="form-control">First Name</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.firstName}" required/>
	
	<label for="inputLastName" class="form-control">Last Name</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.lastName}" required/>
	
	<button class="btn btn-lg btn-primary btn-block btn-signin" id="update" type="submit">Update</button>
</form>
</div>
<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>