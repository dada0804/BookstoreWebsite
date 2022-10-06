<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users - Evergreen Bookstore Administration</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2>Users Management</h2>
	<h3>
	<a href = "user_form.jsp">Create New User</a>
	</h3> 
	</div>
	
	<div align = "center">
	<table border = "1">
		<tr>
			<th>Index</th>
			<th>ID</th>
			<th>Email</th>
			<th>Full Name</th>
			<th>Actions</th>
			</tr>
			<c:forEach var = "user" items = "${listUsers}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td> ${user.userId}</td>
				<td> ${user.email}</td>
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
	
</body>
</html>