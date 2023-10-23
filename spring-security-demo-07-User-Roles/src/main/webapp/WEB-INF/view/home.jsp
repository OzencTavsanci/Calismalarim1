<%@ taglib prefix ="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix ="security" uri="http://www.springframework.org/security/tags"  %>

<html>

<head>
	<title> Welcome to Company Page!!</title>
</head>

<body>
	<h2> Welcome to Company Page!</h2>
	<hr>
	<h3> Welcome to the home page </h3>
	<hr>
	<!-- Display user name and roles -->
	<p>
		User:<security:authentication property="principal.username"/>
		<br> <br>
		Role(s):<security:authentication property="principal.authorities"/>
	</p>
	
	
	<hr>
	<!-- Add a link to point to /leaders.. this is for managers -->
	
	<security:authorize access="hasRole('BIG_BOSS')">
		<p>
			<a href="${ pageContext.request.contextPath }/leaders "> LeaderShip Meeting</a>
			(Only For Managers peeps)
		</p>
	</security:authorize>

	<!-- Add a link to point to /systems.. This is for admins -->
	
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${ pageContext.request.contextPath }/systems "> Admins Meeting</a>
			(Only For Admins peeps)
		</p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath }/logout" method="POST">
			<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>