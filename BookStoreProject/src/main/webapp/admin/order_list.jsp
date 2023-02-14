<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Manage Book Order - Evergreen Bookstore Administration</title>
	<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 
	<link rel="stylesheet" href = "../css/style.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class="pageheading">Book Order Management</h2>
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
			<th>Order ID</th>
			<th>Ordered By</th>
			<th>Book Copies</th>
			<th>Total</th>	
			<th>Payment Method</th>
			<th>Status</th>
			<th>Order Date</th>			
			<th>Actions</th>
			</tr>
			<c:forEach var = "order" items = "${listOrder}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td> ${order.orderId}</td>
				<td> ${order.customer.fullname}</td>
				<td>${order.bookCopies}</td>
				<td><fmt:formatNumber value = "${order.total}" type = "currency" currencySymbol = "$"/></td>
				<td>${order.paymentMethod}</td>
				<td>${order.status}</td>
				<td>${order.orderDate}</td>				
				<td>
				<a href = "view_order?id=${order.orderId}">Details</a>
				<a href = "edit_order?id=${order.orderId}">Edit</a>   
				<a href="javascript:void(0);" class = "deleteLink"  id="${order.orderId}">Delete</a>   </td>
			</tr>
			</c:forEach>
	</table>	
	</div>

	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				orderId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the order with ID " + orderId + " ?")){
					window.location = "delete_order?id=" + orderId; //
				}
		});
	});
	});


</script>	
</body>


</html>