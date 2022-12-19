<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Category - Evergreen Bookstore Administration</title>
<link rel = "stylesheet" type="text/css" href = "../css/style.css">
<script type ="text/javascript" src = "../js/jquery-3.6.1.min.js"></script>
<script type ="text/javascript" src = "../js/jquery.validate.min.js"></script>

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2>Category Management</h2>
	<h3>
	<a href = "category_form.jsp">Create New Category</a>
	</h3> 
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class = "message">
				<i>${message}</i>
			</h4>
		</div>
	</c:if>
	<div align = "center">
	<table border = "1">
		<tr>
			<th>Index</th>
			<th>ID</th>
			<th>Category Name</th>
			<th>Actions</th>
			</tr>
			<c:forEach var = "category" items = "${listCategory}" varStatus = "status">
			<tr>
				<td> ${status.index + 1}</td>
				<td> ${category.categoryId}</td>
				<td> ${category.name}</td>
				<td><a href = "edit_category?id=${category.categoryId}">Edit</a>   
				<a href = "javascript:void(0);" class = "deletelink" id="${category.categoryId}" }>Delete</a></td>
			</tr>
			</c:forEach>
		
		
	
	</table>
	
	</div>


	<%@ include file = "footer.jsp" %>
	
</body>

<script >
$(document).ready(function(){
	$(".deletelink").each(function(){
		$(this).on("click", function(){
			categoryId = $(this).attr("id");
			if(confirm("Are you sure you want to delete the user with ID " +  categoryId + " ?")){
				window.location = "deleteuser?id=" + categoryId;
			}	
		)
		})
	})
});

</script>
</html>