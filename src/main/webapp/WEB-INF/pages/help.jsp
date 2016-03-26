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
<ol>
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
</ol>
<a name="memorizationTechniques"></a>
<h2>Memorization Techniques</h2>
<p>Here are some tips for memorizing new characters. We do not claim to be experts on how memorization works, and 
anecdotally  we see that different techniques work for different people.  We set down here some methods that work
for us.  
<p>We are interesting in learning new techniques for memorizing characters.  If you have one that works, feel free to
share it in an e-mail to jhancock1975@gmail.com .
<dl>
	<dt>Character Memorization Techniques</dt>
	<dd>Imagine the character being written a stroke at a time. Once you can see it being written in your mind correctly
	move on.</dd>
	<dd>Many characters derive their sound from other characters.  Sometimes one character contains another character,
	and is pronounced the same or similarly.  Sometimes you can exploit this sounds like / looks like relationship
	between characters to learn a new character.  Sometimes meanings are also related.  For example, take 是 (shi4) 日 (ri4).
	These characters have the same vowel sound, they are the same tone, and begin with consonants that are pronounced in
	a pysiologically similar manner (put the characters into Google translate and listen to the pronunciation if this
	bit about consonants is confusing). Also, if we decompose 是 into radicals, we see it is composed of 日, 下, and 人.
	These are the characters for sun, underneath, and person. 是 is therefore a representation of a person under the sun.
	One of the meanings of 是 is, "to be."  Knowing all this, how could one forget the meaning, or pronunciation of 是?<dd>
	<dd>Related to the previous technique, make a note of the radicals the character is composed of.  Characters are
	combinations of radicals.  It is easier for some to remember a list of three or four radicals, rather than a list
	of 10 or 20 strokes.</dd>
	<dd>Dump a bucket of ice-water on your head every time you forget a character.  Just kidding!  Hundreds of millions, 
	if not billions of people read characters.  I wager at least one of those folks is an ordinary mortal like us. If
	he or she can do it, why not us?  So go easy on yourself.  If one of the 7,337 characters is not sticking, take a
	break, or move on to the next one.  That particularly annoying character will still be there tomorrow.</dd>
	
	   
</dl>  
<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>