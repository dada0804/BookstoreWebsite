<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">

</head>
<body>
	<%@ include file="header.jsp"%>
	<div align="center">
		<h2 class="pageheading">Administration Dashboard</h2>



	</div>
	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Quick Action</h2>
		<b> <a href="new_book">New Book</a> &nbsp; <a href="user_form.jsp">New
				User</a> &nbsp; <a href="category_form.jsp">New Category</a> &nbsp; <a
			href="customer_form.jsp">New Customer</a> &nbsp;
		</b>
		<hr width="60%" />


		<div align="center">
			<h2 class="pageheading">Recent Sales</h2>
			<div align="center">
				<table border="1" cellpadding="5">
					<tr>
						<th>Order ID</th>
						<th>Ordered By</th>
						<th>Book Copies</th>
						<th>Total Amount</th>
						<th>Payment Method</th>
						<th>Status</th>

						<th>Order Date</th>
					</tr>
					<c:forEach var="order" items="${mostRecentSales}" varStatus="status">
						<tr>
							<td><a href = "view_order?id=${order.orderId}">${order.orderId}</a></td>
							<td>${order.customer.fullname}</td>
							<td>${order.bookCopies}</td>
							<td><fmt:formatNumber value="${order.total}" type="currency"
									currencySymbol="$" /></td>
							<td>${order.paymentMethod}</td>
							<td>${order.status}</td>
							<td>${order.orderDate}</td>

						</tr>
					</c:forEach>
				</table>
			</div>

		</div>

		<hr width="60%" />

		<div align="center">
			<h2 class="pageheading">Recent Reviews</h2>
			<div align = "center">
	<table border = "1">
		<tr>
		
			<th>Book</th>
			<th>Rating</th>
			<th>Headline</th>
			<th>customer</th>
			<th>Review On</th>
			
		</tr>
			<c:forEach var = "review" items = "${mostRecentReviews}" varStatus = "status">
			<tr>
							
				<td> ${review.book.title}</td>
				<td> ${review.rating}</td>
				<td><a href="edit_review?id=${review.reviewId}">${review.headline}</a> </td>
				<td>${review.customer.fullname}</td>
				<td><fmt:formatDate pattern ="MM/dd/yyyy" value='${review.reviewTime}'/></td>
		
				
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>
			<hr width="60%" />

		</div>
		<div align="center">
			<h2 class="pageheading">Statistics</h2>
			Total Users: <a href = "list_users">${totalUsers}</a> &nbsp;&nbsp;&nbsp;&nbsp;
			Total Books: <a href = "list_books">${totalBooks}</a>&nbsp;&nbsp;&nbsp;&nbsp;
			Total Customers: <a href = "list_customer">${totalCustomers}</a> &nbsp;&nbsp;&nbsp;&nbsp;
			Total Reviews: <a href = "list_review">${totalReviews} </a>&nbsp;&nbsp;&nbsp;&nbsp;
			Total Orders: <a href = "list_order">${totalOrders} </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<hr width="60%" />


		</div>




	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>