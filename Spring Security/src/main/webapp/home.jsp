<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
</head>
<body>
	<h1>User Roles</h1>

	<h3>Your roles</h3>
	<c:forEach var="role" items="${user.roles}">
		<li><c:out value="${role.name}" /><br/></li>
	</c:forEach>

	<a href="/logout">Logout</a>
</body>
</html>