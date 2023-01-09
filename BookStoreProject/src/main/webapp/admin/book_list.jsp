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
	<h2 class="pageheading">Book Management</h2>
	<h3>
	<a href = "new_book">Create New Book</a>
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
			<th>Image</th>
			<th>Title</th>
			<th>Author</th>
			<th>Category</th>
			<th>Price</th>
			<th>Last Updated</th>
			<th>Actions</th>
			
		</tr>
			<c:forEach var = "book" items = "${listBook}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td > ${book.bookId}</td>
				<td> 
				<img src = "data:image/jpeg;base64,${book.base64Image}" style = "width:50%">
				</td>
				<td> ${book.title}</td>
				<td> ${book.author}</td>
				<td> ${book.category.getName()}</td>
				<td>$${book.price }</td>
				<td><fmt:formatDate pattern ="MM/dd/yyyy" value='${book.lastUpdateTime}'/></td>
				<td> <a href = "edit_book?id=${book.bookId}">Edit</a>
				<a href = "javascript:void(0);" class = "deleteLink" id = "${book.bookId}">Delete</a></td>
				
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
<script type = "text/javascript">
	$(document).ready(function(){
		$(".deleteLink").each(function(){/* 对每个为deleteLink的function  */
			$(this).on("click",function(){
				bookId=$(this).attr("id");/* this指的是deleteLink */
				if (confirm("Are you sure you want to delete the book with ID " + bookId + " ?")){
					window.location = "delete_book?id=" + bookId; //
				}
		});
	});
	});
</script>	
</body>
</html>