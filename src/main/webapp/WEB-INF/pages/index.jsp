<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="ci" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="blueRootserApp">
<head>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf"%>

<script language='JavaScript' src="<c:url value="/resources/javascript/bower_components/angular-route/angular-route.js" />" ></script>
<script language='JavaScript' src="<c:url value="/resources/javascript/bower_components/angular-sanitize/angular-sanitize.js" />" ></script>
<script language='JavaScript' src="<c:url value="/resources/javascript/app/blueRootserApp.js" />" ></script>
<script language='JavaScript' >var csrfTokenVal="${_csrf.token}"</script>

<title>Blue Rootser 讀漢語 - read Chinese </title>

</head>
<body ng-controller="mainController" ng-cloak>

 <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src='<c:url value="/resources/images/blue-rootser-banner.png"/>'>
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#/">Home</a>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                    <li><form action="/logout" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input class="logout-button nav navbar-nav" type="submit" value="Sign Out" />
						</form>
					</li>
					 <li>
                        <a href="#myArea">My Area</a>
                    </li>
                    </sec:authorize>
					<sec:authorize access="!isAuthenticated()">
                    <li>
                        <a href="#login">Log In</a>
                    </li>
                    <li>
                        <a href="#signup">Sign Up</a>
                    </li>
                    </sec:authorize>
                     <li>
                        <a href="#help">Help</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div id="main" class="col-lg-12" >
                <!-- trying second ng cloak here because I see flicker after clicking navbar -->
                <div ng-view ng-cloak>{{message}}
                		<div id="articleDiv" ng-bind-html="articleText" ></div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container -->

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/javascript/bower_components/bootstrap/dist/js/bootstrap.js" />" ></script>

</body>
</html>