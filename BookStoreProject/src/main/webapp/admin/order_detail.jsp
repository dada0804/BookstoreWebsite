<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Order Detail - Evergreen Bookstore Administration</title>
	<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 
	<link rel="stylesheet" href = "../css/style.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class="pageheading">Details of Order ID: ${order.orderId }</h2>
	<h3>Order Overview</h3>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<div align = "center">
		<table >
		
		<tr>
			<td><b>Ordered By: </b></td>
			<td>${order.customer.fullname}</td>
		</tr>
		<tr>
			<td><b>Book Copies: </b></td>
			<td>${order.bookCopies}</td>
		</tr>
		<tr>
			<td><b>Total Amount: </b></td>
			<td><fmt:formatNumber value = "${order.total}" type = "currency" currencySymbol = "$"/></td>
		</tr>
		<tr>
			<td><b>Recipient Name: </b></td>
			<td>${order.recipientName}</td>
		</tr>
		<tr>
			<td><b>Recipient Phone: </b></td>
			<td>${order.recipientPhone}</td>
		</tr>
		<tr>
			<td><b>Ship To: </b></td>
			<td>${order.shippingAddress} </td>
		</tr>
		<tr>
			<td><b>Payment Method: </b></td>
			<td>${order.paymentMethod}</td>
		</tr>
		<tr>
			<td><b>Order Status: </b></td>
			<td>${order.status}</td>
		</tr>
		<tr>
			<td><b>Order Date: </b></td>
			<td>${order.orderDate}</td>
		</tr>
		
		</table>
	
	</div>
	<div align = "center">
	<h3>Ordered Books: </h3>
	<table border = "1">
		<tr>
			<th>Index</th>
			<th>Book Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>	
			<th>SubTotal</th>
			</tr>
			<c:forEach var = "detail" items = "${order.orderDetails}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td> ${detail.book.title}</td>
				<td> ${detail.book.author}</td>
				<td>${detail.book.price}</td>
				<td>${detail.quantity}</td>
				<td><fmt:formatNumber value = "${detail.subtotal}" type = "currency" currencySymbol = "$"/></td>			
			</tr>
			
			</c:forEach>
			<tr>
			<td colspan = 4 align = "right"><b>Total:</b></td>
			<td><b>${order.bookCopies}</b></td>
			<td><b>${order.total}</b></td>
			</tr>
	</table>
	<br/>
		<a href = "edit_order?id=${order.orderId}">Edit Order</a>   
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href = "delete_order?id=${order.orderId}">Delete Order</a></td>	
	</div>

	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				categoryId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the category with ID " + categoryId + " ?")){
					window.location = "delete_category?id=" + categoryId; //
				}
		});
	});
	});


</script>	
</body>


</html>