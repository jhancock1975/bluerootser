<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="blueRootserApp">
<head>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf"%>

<script language='JavaScript' src="<c:url value="/resources/javascript/bower_components/angular-route/angular-route.js" />" ></script>
<script language='JavaScript' src="<c:url value="/resources/javascript/app/blueRootserApp.js" />" ></script>
<link href="<c:url value="/resources/css/logo-nav.css" />" rel="stylesheet">

<title>Blue Rootser 讀漢語 - read Chinese </title>

</head>
<body ng-controller="mainController">
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
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#login">Login</a>
                    </li>
                    <li>
                        <a href="#myArticles">My Articles</a>
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
            <div id="main" class="col-lg-12">
                <h1>Boost Your Chinese Reading</h1>
                <p>The latest articles are below.  Highlight text to look up in Wiktionary</p>
                <div ng-view>{{message}}</div>
            </div>
        </div>
    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="<c:url value="/resources/javascript/bower_components/jquery/dist/jquery.js" />" ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/javascript/bower_components/bootstrap/dist/js/bootstrap.js" />" ></script>
</body>
</html>