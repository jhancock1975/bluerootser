<%@taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ include file="/jspfrag/bootstrap-head-elements.jspf"%>

<link href="/resources/css/signin.css" rel="stylesheet">

<title>Blue Rootser - Please Login</title>
<script language='JavaScript'>
	csrfTokenName="${_csrf.parameterName}";
	csrfToken="${_csrf.token}";
</script>
</head>
<body >
<div class="container">
      <form class="form-signin" action="/login" method="post">
      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="E-mail address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
    
	<%@ include file="/jspfrag/common-footer.jspf"%>
</body>
</html>