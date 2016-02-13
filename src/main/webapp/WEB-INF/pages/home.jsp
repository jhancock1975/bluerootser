<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	 <%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Welcome!</h1>
		 
		<sec:authorize access="hasRole('ROLE_USER')">
        <h1>You are in the user role</h1>
        </sec:authorize>
        
        <p>Click <a href="/hello">here</a> to see a greeting.</p>
	<%@ include file="/jspfrag/common-footer.jspf"%>
    </body>
</html>