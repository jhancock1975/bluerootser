<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<title>My Area On Bluerootser</title>

</head>
<body >
<h1>Your Account Information</h1>
<core:forEach var="user" items="${curUserList}">
	<h1> username = ${user.userName}</h1>
	</core:forEach>
</body>
<p>${curUser.userName}</p>
<p>${curUser.firstName}</p>
<p>${curUser.lastName}</p>
<p>${curUser.email}</p>
</html>