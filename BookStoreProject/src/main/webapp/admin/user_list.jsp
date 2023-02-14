<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Users - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href = "../css/style.css">
	<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class="pageheading">Users Management</h2>
	<h3>
	<a href = "create_user">Create New User</a>
	</h3> 
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">
				${message}
			</h4>
		</div>
	</c:if>
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
				<td> ${user.fullName}</td>
				<td><a href = "edit_user?id=${user.userId}">Edit</a>   
				<a href = "javascript:void(0))" class="deleteLink" id="${user.userId}")>Delete</a></td><!-- to do nothing -->
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				userId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the user with ID " + userId + " ?")){
					window.location = "delete_user?id=" +userId; //the DeleteUserServlet 
				}
		});
	});
	});


/* function confirmDelete(userId){
	/* if(userId == 1){
		alert("The default admin user account cannot be deleted.");
	}
	
	else  */
		/* if (confirm("Are you sure you want to delete the user with ID " + userId + " ?")){
		window.location = "delete_user?id=" +userId; //the DeleteUserServlet 
	}
} */
 
</script>
	
</body>


</html>