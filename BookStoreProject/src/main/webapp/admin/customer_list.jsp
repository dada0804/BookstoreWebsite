<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Book - Evergreen Bookstore Administration</title>
<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 
	<link rel="stylesheet" href = "../css/style.css">
</head>
<body>

<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class="pageheading">Customer Management</h2>
	<h3>
	<a href = "create_customer">Create New Customer</a>
	</h3> 
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align = "center">
	<table border = "1">
		<tr>
			<th>Index</th>
			<th>ID</th>
			<th>Email</th>
			<th>Full Name</th>
			<th>City</th>
			<th>Country</th>
			<th>Registered Date</th>
			<th>Actions</th>
			
		</tr>
			<c:forEach var = "customer" items = "${listCustomer}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td > ${customer.customerId}</td>				
				<td> ${customer.email}</td>
				<td> ${customer.fullname}</td>
				<td> ${customer.city}</td>
				<td>${customer.country }</td>
				<td><fmt:formatDate pattern ="MM/dd/yyyy" value='${customer.registerDate}'/></td>
				<td> <a href = "edit_customer?id=${customer.customerId}">Edit</a>
				<a href = "javascript:void(0);" class = "deleteLink" id = "${customer.customerId}">Delete</a></td>
				
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				customerId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the customer with ID " + customerId + " ?")){
					window.location = "delete_customer?id=" + customerId; //
				}
		});
	});
	});
</script>	
</body>
</html>