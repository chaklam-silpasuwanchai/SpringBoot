<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>

<label>Add Contract</label>
            
<form action="addContract" action="GET">
	
	Choose Product: <select name="pid">
	<c:forEach items="${products}" var="product" >
		<option value="${product.id}">${product.name}</option>
	</c:forEach>
	</select> <br/>
	
	Date signed: <input type="date" name="date"><br> 
	Contract Price: <input type="text" name="price"><br> 
	<input type="submit">
</form>

</body>
</html>