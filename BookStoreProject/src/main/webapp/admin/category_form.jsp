<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New User</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
<h2>
	<c:if test = "${category != null }">
	Edit Category
	
	</c:if>
		<c:if test = "${category == null }">
	Create New Category
		</c:if>
	
	
	</h2>
	

    
    </div>   
    
    
    <div align = "center">
	<c:if test = "${category != null }"> 
    	<form action = "update_category" method = "post" >
	<input type = "hidden" name = "id" value = "${category.categoryId}">
    		</c:if>
    		<c:if test = "${category == null }"> 
    	<form action = "create_category" method = "post" onsubmit = "create_category" >
    		</c:if>
    	
    	<table>
    	<tr>
    
    	<td align = "right">Name:     	</td>
    	
    	<td align = "left"><input type = "text" name = "name" id = "name" size = "20" value = "${category.name}"/></td>
    	
    	
    	<tr><td>&nbsp;</td></tr>
    	<tr>
    	<td colspan = "2" align = "center">
    	
    	<input type = "submit" value = "Save">
    	<input type = "button" value = "Cancel" onclick = "javascript:history.go(-1);">
    	</td> 
    	
    	</tr>
    	</table>
    	</form>
    
    </div>
    
    
    
    


	<%@ include file = "footer.jsp" %>
	
</body>

<script type = "text/javascript">
	function validateFormInput(){
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
	}
</script>



</html>