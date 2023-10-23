<%@ taglib prefix ="form" uri="http://www.springframework.org/tags/form"  %>

<html>

<head>
	<title> Welcome to Company Page!!</title>
</head>

<body>
	<h2> Welcome to Company Page!</h2>
	<hr>
	<h3> Welcome to the home page </h3>
	<form:form action="${pageContext.request.contextPath }/logout" method="POST">
			<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>