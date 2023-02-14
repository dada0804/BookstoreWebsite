<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Order - Evergreen Bookstore Administration</title>
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="../css/style.css">

</head>
<body>
	<%@ include file="header.jsp"%>
	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId }</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<form action="update_order" method="post" id="orderForm">
			<table>

				<tr>
					<td><b>Ordered By: </b></td>
					<td>${order.customer.fullname}</td>
				</tr>
				<tr>
					<td><b>Order Date: </b></td>
					<td>${order.orderDate}</td>
				</tr>
				<tr>
					<td><b>Recipient Name: </b></td>
					<td><input type="text" value="${order.recipientName}"
						name="recipientName" /></td>
				</tr>
				<tr>
					<td><b>Recipient Phone: </b></td>
					<td><input type="text" value="${order.recipientPhone}"
						name="recipientPhone" /></td>
				</tr>
				<tr>
					<td><b>Ship To: </b></td>
					<td><input type="text" value="${order.shippingAddress}"
						name="address" /></td>
				</tr>
				<tr>
					<td><b>Payment Method: </b></td>
					<td><select name="paymentMethod">
							<option value="Cash On Delivery">Cash on Delivery</option>
					</select></td>
				</tr>
				<tr>
					<td><b>Order Status: </b></td>
					<td><select name="orderStatus">
							<option value="Processing" <c:if test="${order.status eq 'Processing'}" >selected = 'selected' </c:if>>Processing</option>
							<option value="Shipping" <c:if test="${order.status eq 'Shipping'}" >selected = 'selected' </c:if>>Shipping</option>
							<option value="Delivered" <c:if test="${order.status eq 'Delivered'}"> selected = 'selected' </c:if>>Delivered</option>
							<option value="Completed" <c:if test="${order.status eq 'Completed'}"> selected = 'selected' </c:if>>Completed</option>
							<option value="Canceled" <c:if test="${order.status eq 'Canceled'}"> selected = 'selected' </c:if>>Canceled</option>

					</select></td>
				</tr>


			</table>

			<div align="center">
				<h3>Ordered Books:</h3>
				<table border="1">
					<tr>
						<th>Index</th>
						<th>Book Title</th>
						<th>Author</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>SubTotal</th>
						<th>Remove</th>
					</tr>
					<c:forEach var="detail" items="${order.orderDetails}"
						varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${detail.book.title}</td>
							<td>${detail.book.author}</td>
							<td>
							<input type = "hidden" name="price" value = "${detail.book.price}"} />
							
							<fmt:formatNumber value="${detail.book.price}"
									type="currency" currencySymbol="$" />
							</td>
							<td>
							<input type = "hidden" name="bookId" value = "${detail.book.bookId}"} />
							<input type="text" name="quantity${status.index+1 }"
								value="${detail.quantity}" size="5" /></td>
							<td><fmt:formatNumber value="${detail.subtotal}"
									type="currency" currencySymbol="$" /></td>
							<td><a href="remove_book_from_order?id=${detail.book.bookId}">Remove</a>
						</tr>

					</c:forEach>
					<tr>
						<td colspan=4 align="right"><b>Total:</b></td>
						<td><b>${order.bookCopies}</b></td>
						<td><b><fmt:formatNumber value="${order.total}"
									type="currency" currencySymbol="$" /></b></td>
					</tr>
				</table>
			</div>
			
			<div align = "center">
			<br/>
			<a href = "javascript:showAddBookPopup()">Add Books</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "submit" value = "Save" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type = "button" value = "Cancel" onClick="javascript:location.href='list_order';" />
			
			</div>
		</form>
	</div>



	<%@ include file="footer.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#orderForm").validate({
			rules : {
				recipientName:"required",
				recipientPhone:"required",
				address: "required",
				<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
				quantity${status.index+1}:{
					required: true,
					number: true,
					min: 1	
				},
				</c:forEach>
			},
			messages : {
				recipientName:"Please enter recipient name.",
				recipientPhone:"Please enter recipient phone.",
				address: "Please enter you shipping address.",
				<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
				quantity${status.index+1}:{
					required: "Please enter quantity.",
					number:"Quantity must be a number.",
					min:"Quantity must be greater than 0."
					},
				</c:forEach>
			},
		});
	});


</script>

<script> 
function showAddBookPopup(){
	var width = 500;
	var height = 280;
	var left =(screen.width - width)/2;
	var top = (screen.height - height)/2;

	window.open("add_book_form", "_blank","width="+width +",height=" + height +",top="+ top + ",left="+left);
	
}

</script>
</body>


</html>