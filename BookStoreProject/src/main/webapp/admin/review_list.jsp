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
	<h2 class="pageheading">Review Management</h2>
	
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
			<th>Book</th>
			<th>Rating</th>
			<th>Headline</th>
			<th>customer</th>
			<th>Review Date</th>
			<th>Actions</th>
			
		</tr>
			<c:forEach var = "review" items = "${listReview}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td > ${review.reviewId}</td>				
				<td> ${review.book.title}</td>
				<td> ${review.rating}</td>
				<td> ${review.headline}</td>
				<td>${review.customer.fullname}</td>
				<td><fmt:formatDate pattern ="MM/dd/yyyy" value='${review.reviewTime}'/></td>
				<td> <a href = "edit_review?id=${review.reviewId}">Edit</a>
				<a href = "javascript:void(0);" class = "deleteLink" id = "${review.reviewId}">Delete</a></td>
				
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				reviewId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the review with ID " + reviewId + " ?")){
					window.location = "delete_review?id=" + reviewId; //
				}
		});
	});
	});
</script>	
</body>
</html>