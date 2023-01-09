<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
       
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Book</title>
	<link rel="stylesheet" href = "../css/style.css">
	<link rel="stylesheet" href="../css/jquery-ui.min.css"> 
	<script type = "text/javascript" src="../js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery.validate.min.js"></script> 
	<script type = "text/javascript" src="../js/jquery-ui.min.js"></script> 
		
	
	

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
<h2 class="pageheading">
	<c:if test = "${book != null }">
	Edit Book
	
	</c:if>
		<c:if test = "${book == null }">
	Create New Book
		</c:if>
	
	
	</h2>
	

    
    </div>   
    
    
    <div align = "center">
	<c:if test = "${book != null }"> 
    	<form id = "bookForm" action = "update_book" method = "post" " enctype = "multipart/form-data">
	<input type = "hidden" name = "bookId" value = "${book.bookId}">
    		</c:if>
    		<c:if test = "${book == null }"> 
    	<form id = "bookForm" action = "create_book" method = "post" enctype = "multipart/form-data">
    		</c:if>
    	
    	<table class="form">
    	<tr>
    	<td>Category:</td>
    	<td>
    		<select name = "category">
    			<c:forEach items = "${listCategory}" var = "category">
    			<c:if test = "${category.categoryId eq book.category.categoryId }">
    				<option value = "${category.categoryId }" selected>
    				</c:if>
    				<c:if test = "${category.categoryId ne book.category.categoryId }">
    				<option value = "${category.categoryId }" >
    				</c:if>	
    				${category.name}</option>	</c:forEach>
    		</select>
    	</td>
    	</tr>
    	<tr>
    
    	<td align = "right">Title:     	</td>
    	
    	<td align = "left"><input type = "text" name = "title" id = "title" size = "20" value = "${book.title}"/></td>
    	
    	
    	</tr>
    	<tr>
    	<td align = "right"> Author:     	</td>
    	<td align = "left"><input type = "text" name = "author" id = "author" size = "20" value = "${book.author}" /></td>    	
    	</tr>
    	
    	<tr>
    
    	<td align = "right">ISBN:     	</td>
    	
    	<td align = "left"><input type = "text" name = "ISBN" id = "isbn" size = "20" value = "${book.isbn}"/></td>
    	
    	
    	</tr>
    	
    	<tr>
	    	<td align = "right">Publish Date:     	</td>
	    	<td align = "left"><input type = "text" name = "publishDate" id = "publishDate" size = "20" value ="<fmt:formatDate pattern ="MM/dd/yyyy" value='${book.publishDate}'/>"/></td>	
	    </tr>
    
    	<tr>
	    	<td align = "right">Book Image:     	</td>
	    	<td align = "left"><input type = "file" name = "bookImage" id = "bookImage" size = "20" /><br>
	    	
	    	<img id = "thumbnail" alt = "Image Preview" style ="width:20%; margin-top: 10px"
	    	src = "data:image/jpeg;base64,${book.base64Image}"
	    	/>
	    	
	    	</td>	
	    	
	    </tr>
	    
	    <tr>
	    	<td align = "right">Price:     	</td>
	    	<td align = "left"><input type = "text" name = "price" id = "price" size = "20" value = "${book.price}"/></td>	
	    </tr>
	    <tr>
	    	<td align = "right">Description:     	</td>
	    	<td align = "left"><textarea rows = "5" cols = "50" name = "description" id = "description" value = "">"${book.description}"</textarea></td>	
	    </tr>
	    
    	<tr><td>&nbsp;</td></tr>
    	<tr>
    	<td colspan = "2" align = "center">
    	
    	<button type = "submit">Save</button>&nbsp;&nbsp;&nbsp;
    	<button id="buttonCancel">Cancel</button>
    	</td> 
    	
    	</tr>
    	</table>
    	</form>
    
    </div>
	<%@ include file = "footer.jsp" %>
	

<script type = "text/javascript">
$(document).ready(function(){
	$( "#publishDate" ).datepicker();
	$("#bookImage").change(function(){
		showImageThumbnail(this);
	})
	$("#bookForm").validate({
		rules:{
			title:"required",
			author:"required",
			category:"required",
			ISBN:"required",
			publishDate:"required",
			
			<c:if test="$(book == null)">
			bookImage:"required",
			</c:if>
			price:"required",
			description:"required"
		},
		messages:{
			title:"Please enter book title",
			author:"Please enter book author ",
			category:"Please enter book category",
			ISBN:"Please enter book isbn",
			publishDate:"Please enter book publish date",
			bookImage:"Please enter book image",
			price:"Please enter book price",
			description:"Please enter book description"
		}
	});
	
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});

function showImageThumbnail(fileInput){
	var file = fileInput.files[0];
	var reader = new FileReader(); 
	reader.onload = function(e){
		$('#thumbnail').attr('src',e.target.result);
	};
	reader.readAsDataURL(file);
}


	/* function validateFormInput(){
		var fieldEmail = document.getElementById("email");
		var fieldFullname = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");

		if(fieldEmail.value.length == 0){
			alert("Email is required");
			fieldFullname.focus();
			return false;
		}
		
		if(fieldFullname.value.length == 0){
			alert("Fullname is required");
			fieldEmail.focus();
			return false;
		}
		
		if(fieldPassword.value.length == 0){
			alert("Password is required");
			fieldPassword.focus();
			return false;
		}
		
		return true; 
	} */
</script>

</body>

</html>