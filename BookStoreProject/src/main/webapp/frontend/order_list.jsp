<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>My Order History - Evergreen Bookstore Administration</title>
	<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 
	<link rel="stylesheet" href = "css/style.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class="pageheading">My Order History</h2>
	</div>

	<c:if test="${fn:length(listOrder) == 0} ">
	<div align = "center">
			<h3>You have not placed any orders yet.</h3>
	</div>
	</c:if>
	
	<c:if test="${fn:length(listOrder)  > 0}">
	<div align = "center">
	<table border = "1" cellpadding = "5">
		<tr>
			<th>Index</th>
			<th>Order ID</th>
			<th>Quantity</th>
			<th>Total Amount</th>	
			<th>Order Date</th>			
			<th>Status</th>
			<th>Actions</th>
			</tr>
			<c:forEach var = "order" items = "${listOrder}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td> ${order.orderId}</td>
				<td>${order.bookCopies}</td>
				<td><fmt:formatNumber value = "${order.total}" type = "currency" currencySymbol = "$"/></td>
				<td>${order.orderDate}</td>		
				<td>${order.status}</td>						
				<td>
				<a href = "show_order_detail?id=${order.orderId}">View Details</a>
			</tr>
			</c:forEach>
	</table>	
	</div>
	</c:if>

	<%@ include file = "footer.jsp" %>
<!-- <script type = "text/javascript">
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


</script>	 -->
</body>


</html>