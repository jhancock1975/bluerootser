<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Welcome!</h1>
		 
		<sec:authorize access="hasRole('ROLE_USER')">
        <h1>You are in the user role</h1>
        </sec:authorize>
        
        <p>Click <a href="/hello">here</a> to see a greeting.</p>
    	<div id="articleDiv" ng-controller="mainController" ng-bind-html="artcileHtml"></div>
	<%@ include file="/jspfrag/common-footer.jspf"%>
    </body>
</html>