<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="ci" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="blueRootserApp">
<head>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf"%>
<title>Blue Rootser 讀漢語 - Help </title>
</head>
<body>
<h1>How to use this site</h1>
<h2>Improve your reading</h2>
<p>The home page for this site displays 10 recent news articles from Taiwan's
 <a href="http://www.cna.com.tw">Central News Agency</a>, or 中央社 (Zhongyang She).</p>
<p>We recommend you use the site in the following way in order to improve your reading:</p>
<ul>
	<li>Read through an article.
	<li>Move your mouse pointer over a character, or the first character of a compound word.
	<li>A box will pop up with the pronunciation and English definition of the word.  We call this a 
	pop-up definition.  An example of what you should see is below.  If you do not get a pop-up definition,
	please keep in mind we recommend using Google Chrome for this site.
	see is below.  Here is an example of a pop-up definition:
	<img src='<c:url value="/resources/images/text-example.png"/>'>
	<li><i><b>Remember</b></i> the pronunciation and the meaning of the word. Click <a href="#memorizationTechniques">here</a> for
		information on memorization techniques.
	<li>Read through the article again, without using the pop-up dictionary, and see if you understand it. 
	<li>If you find yourself using the pop-up dictionary so much that you can't remember the meaning and
		pronunciation of all the words, apply this technique on a smaller part of the article, such as a 
		paragraph or sentence.
</ul>

<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>