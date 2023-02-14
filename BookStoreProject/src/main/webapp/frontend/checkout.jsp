<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<!-- 以上注意先后顺序！ -->
</head>
<body>
	<%@ include file="header.jsp"%>

	<div align="center">
		<h2>
			Review Your Book Order Details <a href="edit_order">Edit</a>
		</h2>

		<c:set var="cart" value="${sessionScope.cart}" />
		<c:set var="loggedCustomer" value="${sessionScope.loggedCustomer}" />
			<c:set var="cart" value="${sessionScope.cart}" />
		<c:if test="${cart.totalItems == 0 }">
			<h2>There is no order for checkout currently.</h2>
		</c:if>

		<c:if test="${cart.totalItems != 0 }">
		<div>
			<table border="1">
				<tr>
					<th>No.</th>
					<th colspan="2">Book</th>
					<th>Author</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Subtotal</th>
				</tr>
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td valign="middle"><img
							src="data:image/jpg;base64, ${item.key.base64Image}"
							class="book-small" /></td>
						<td><span id="book-title">${item.key.title}</span></td>
						<td>${item.key.author}</td>
						<td>${item.value}</td>

						<td><fmt:formatNumber currencySymbol="$"
								value="${item.key.price}" type="currency" /></td>
						<td><fmt:formatNumber currencySymbol="$"
								value="${item.key.price*item.value}" type="currency" /></td>

					</tr>
				</c:forEach>

				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><b>${cart.totalQuantity} book(s)</b></td>
					<td>Total:</td>
					<td colspan="3"><b><fmt:formatNumber
								value="${cart.totalAmount}" type="currency" /></b></td>
				</tr>
			</table>
		</div>
		<br /> <br />

		<div align="center">
			<h3>Your Shipping Information</h3>
			<form action="place_order" method="post" id="orderForm">
				<table class="normal">
					<tr>
						<td>Recipient Name:</td>
						<td><input type="text" name="recipientName"
							value="${loggedCustomer.fullname}" /></td>
					</tr>
					<tr>
						<td>Recipient Phone:</td>
						<td><input type="text" name="recipientPhone"
							value="${loggedCustomer.phone}" /></td>
					</tr>
					<tr>
						<td>Street Address:</td>
						<td><input type="text" name="streetAddress"
							value="${loggedCustomer.address}" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><input type="text" name="city"
							value="${loggedCustomer.city}" /></td>
					</tr>
					<tr>
						<td>Zip Code:</td>
						<td><input type="text" name="zipCode"
							value="${loggedCustomer.zipcode}" /></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><input type="text" name="country"
							value="${loggedCustomer.country}" /></td>
					</tr>
				</table>

				<h3>Payment</h3>
				Choose Your Payment Method: &nbsp; &nbsp;&nbsp; <select
					name="paymentMethod">
					<option value="Cash On Delivery">Cash on Delivery</option>
					<option value="creditCard">Credit Card</option>

				</select>

				<table class="normal">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit">Place Order</button></td>
						<td><a href="${pageContext.request.contextPath}/">Continue
								Shopping</a></td>
					</tr>
				</table>


			</form>
		</div>

	</div>

		</c:if>

	<%@ include file="footer.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#orderForm").validate({
				rules : {
					recipientName : "required",
					recipientPhone : "required",
					streetAddress : "required",
					zipCode : "required",
					country : "required",
					city : "required",
				},
				messages : {
					recipientName : "Please enter your name",
					recipientPhone : "Please enter your phone ",
					streetAddress : "Please enter street address",
					zipCode : "Please enter zip code",
					country : "Please enter country",
					city : "Please enter city"
				}
			});

		});
	</script>

</body>

</html>