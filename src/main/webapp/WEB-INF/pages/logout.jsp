<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Are you sure you want to log out?</h1>

	<form action="/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
    </form>
    
	<%@ include file="/jspfrag/common-footer.jspf"%>
    </body>
</html>