<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<html ng-app="store">
<head>
<title>Hello world page</title>
</head>
<body ng-controller="StoreController">
	<textarea ng-model="message" cols="40" rows="10"></textarea>
	<button type="button" class="btn btn-default" ng-click="save()">Save</button>
	<button class="btn btn-primary" ng-click="clear()">Clear</button>
	Number of characters left: <span ng-bind="left()"></span>
	<script
		src="<c:url value="/resources/javascript/bower_components/angular/angular.js" />"></script>
		<script
		src="<c:url value="/resources/javascript/bower_components/angular-bootstrap/ui-bootstrap.js" />"></script>
	<script src="<c:url value="/resources/javascript/app/app.js" />"></script>
	 <link href="<c:url value="/resources/javascript/bower_components/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet">
</body>
</html>
