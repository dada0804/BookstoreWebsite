<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<!-- 以上注意先后顺序！ -->
</head>
<body>
	<%@ include file="header.jsp"%>

	<div align="center">
		<h1>Book Store</h1>
		<h2>Shopping Cart Detail</h2>

		<c:set var="cart" value="${sessionScope.cart}" />
		<c:if test="${cart.totalItems == 0 }">
			<h2>There is no book in the cart</h2>
		</c:if>

		<c:if test="${cart.totalItems != 0 }">
				<form action = "update_cart" method = "post" id = "cartForm">
				<div>
					<table border="1">
						<tr>
							<th>No.</th>
							<th colspan = "2">Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>Remove</th>
						</tr>
						<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td valign="middle">
								<img
									src="data:image/jpg;base64, ${item.key.base64Image}"
									class="book-small" /></td>
									<td> 
									<span id = "book-title">${item.key.title}</span></td>
									<input type = "hidden" name = "bookId" value = "${item.key.bookId}" />

								<td><input type = "text" name = "quantity${status.index+1 }" value = "${item.value}" size = "5"/></td>

								<td><fmt:formatNumber value="${item.key.price}"
										type="currency" /></td>
								<td><fmt:formatNumber value="${item.key.price*item.value}"
										type="currency" /></td>
								<td><a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a></td>

							</tr>
						</c:forEach>

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><b>${cart.totalQuantity} book(s)</b></td>
							<td>Total:</td>
							<td colspan="2"><b><fmt:formatNumber
										value="${cart.totalAmount}" type="currency" /></b></td>
						</tr>
					</table>
					</div>
					<br/><br/>
					<div>
					<table class = "normal">
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td></td>
						<td><button type = "submit">Update Cart</button></td>
						<td><input type = "button" id = "clearCart" value = "Clear Cart"/></td>
						<td><a href = "${pageContext.request.contextPath}/">Continue Shopping</a></td>
						<td><a href = "checkout">Checkout</a>
					</tr>
					</table>
					</div>
				</form>
			</div>
		</c:if>

	
	<%@ include file="footer.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#cartForm").validate({
				rules : {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index+1}:{
						required: true,
						number: true,
						min: 1	
					},
					</c:forEach>
				},
				messages : {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index+1}:{
						required: "Please enter quantity.",
						number:"Quantity must be a number.",
						min:"Quantity must be greater than 0."
						},
					</c:forEach>
				},
			});
			
			$("#clearCart").click(function(){
				window.location = "clear_cart";
			});
			
		});
	</script>

</body>

</html>