<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf" %>
        <title>Spring Security Example</title>
    </head>
    <body>
		 
	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- content for logged in user goes here -->
        </sec:authorize>
        <div id="articleHeaderDiv">
	                <h1>Boost Your Chinese Reading</h1>
	                <p>The latest articles are below.  Mouse over or touch text to look up word. 
	                	Click <a href="#help"><i>here</i></a> to learn more about using this site.
	                </p>
	                <hr class="bannerHr"  />
                </div>     
    	<div id="articleDiv" ng-controller="mainController" ng-bind-html="artcileHtml" highlight class="tip"></div>
	<%@ include file="/jspfrag/common-footer.jspf"%>
    </body>
</html>
