<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<link href="/resources/css/signin.css" rel="stylesheet">
<link href="/resources/css/myArea.css" rel="stylesheet">

<title>My Area On Bluerootser</title>

</head>
<body >
<div class="container">
<h2 class="form-signin-heading">Your Account Information</h2>
<form class="form-signin"  name="updateUserForm" id="updateUserForm"  novalidate ng-submit="updateUser(updateUserForm.$valid)">
	<label>Change any values below and click submit.  Changes will be saved to your account.</label> 
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
	<input type="hidden"  name="curUserId" id="curUserId" value="${curUser.userName}"/>
	
	<label for="inputEmail" id="inputEmailLabel">Email address</label>
	<input type="email" name="email" id="inputEmail" class="form-control" value="${curUser.email}" autofocus/>
	
	<div id="myAreaPasswdMsgDiv">
		<label>To update your password, enter your current password.  Then, enter a new password twice to confirm the correct value.</label>
	</div>
	
	<label for="inputPassword" >Current Password</label>
	<input type="password" name="password" id="inputPassword" class="form-control"/>
	
	<label for="newPassword1" id='newPasswdLabel1' >New Password</label>
	<input type='password' name='newPassword1' id='inputNewPassword1' class='form-control'/>
	
	<label for="newPassword2" id='newPasswdLabel2'>New Password (Again)</label>
	<input type='password' name='newPassword2' id='inputNewPassword2' class='form-control'/>
	
	<label for="inputDob" >Birthday</label>
	<input type="text" name="dob" id="inputDob" class="form-control" value="${curUser.dob}" required/>
	
	<label for="inputFirstName" >First Name</label>
	<input type="text" name="firstName" id="inputFirstName" class="form-control" value="${curUser.firstName}" required/>
	
	<label for="inputLastName" >Last Name</label>
	<input type="text" name="lastName" id="inputLastName" class="form-control" value="${curUser.lastName}" required/>
	
	<button class="btn btn-lg btn-primary btn-block btn-signin" id="update" type="submit">Update</button>
</form>
</div>
<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>