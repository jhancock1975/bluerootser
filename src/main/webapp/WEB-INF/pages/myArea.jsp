<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<title>My Area On Bluerootser</title>

</head>
<body >
<h1>Your Account Information</h1>

<p>${curUser.userName}</p>
<p>${curUser.firstName}</p>
<p>${curUser.lastName}</p>
<p>${curUser.email}</p>
<label for="inputEmail" class="sr-only">Email address</label>
<input type="text" name="username" id="inputEmail" class="form-control" value="${curUser.email}" required autofocus>
<label for="inputPassword" class="sr-only">Password</label>
<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
<label for="inputDob" class="sr-only">Birthday</label>
<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required>

</body>
</html>