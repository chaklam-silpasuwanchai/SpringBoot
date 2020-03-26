<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>

<label>Check Revenue Recognition</label>
          
<form action="/checkRecognizedRevenue" action="GET">
	
	Contract ID: <select name="cid">
	<c:forEach items="${contracts}" var="contract" >
		<option value="${contract.id}">Product ID: ${contract.product.id}; 
		Revenue: ${contract.revenue_}, Date Signed: ${contract.dateSigned}</option>
	</c:forEach>
	</select> <br/>
	Date as of: <input type="date" name="date"><br> 
	<input type="submit">
</form>

</body>
</html>