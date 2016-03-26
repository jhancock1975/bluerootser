<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html ng-app="store">
<head>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
<title>Hello world page</title>
</head>
<body ng-controller="StoreController">

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- content for logged in users goes here -->
	</sec:authorize>
        
	<textarea ng-model="message" cols="40" rows="10"></textarea>
	<button type="button" class="btn btn-default" ng-click="save()">Save</button>
	<button class="btn btn-primary" ng-click="clear()">Clear</button>
	Number of characters left: <span ng-bind="left()"></span>
	<form action="/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
    </form>
<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>
