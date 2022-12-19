<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New User</title>
<link rel = "stylesheet" type="text/css" href = "../css/style.css">
<script type ="text/javascript" src = "../js/jquery-3.6.1.min.js"></script>
<script type ="text/javascript" src = "../js/jquery.validate.min.js"></script>


</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
<h1 class = "pageheading">
	<c:if test = "${user != null }">
	Edit User
	
	</c:if>
		<c:if test = "${user == null }">
	Create New User
		</c:if>
	
	
	</h1>
	

    
    </div>   
    
    
    <div align = "center">
	<c:if test = "${user != null }"> 
    	<form action = "update_user" method = "post" id = "userForm">
	<input type = "hidden" name = "userId" value = "${user.userId}">
    		</c:if>
    		<c:if test = "${user == null }"> 
    	<form action = "create_user" method = "post" id = "userForm">
    		</c:if>
    	
    	<table>
    	<tr>
    
    	<td align = "right">Email:     	</td>
    	
    	<td align = "left"><input type = "text" name = "email" id = "email" size = "20" value = "${user.email}"/></td>
    	
    	
    	</tr>
    	<tr>
    	<td align = "right">Full Name:     	</td>
    	<td align = "left"><input type = "text" name = "fullname" id = "fullname" size = "20" value = "${user.fullName}" /></td>
    	
    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Password:     	</td>
    	<td align = "left"><input type = "text" name = "password" id = "password" size = "20" value = "${user.password}"/></td>
    	
    	
    	</tr>
    	<tr><td>&nbsp;</td></tr>
    	<tr>
    	<td colspan = "2" align = "center">
    	
    	<button type = "submit" value = "Save">Save</button>
    	<button id = "cancelButton">Cancel</button>
    	</td> 
    	
    	</tr>
    	</table>
    	</form>
    
    </div>
    
    
    
    


	<%@ include file = "footer.jsp" %>
	
</body>

<script type = "text/javascript">
		
	$(document).ready(function(){
		$("#userForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				fullname: "required",
				password: "required"
			},
			messages: {
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				fullname: "Please enter full name",
				password: "Please enter password"
			}
		});
		$("#cancelButton").click(function(){
			history.go(-1);
		})
	});



</script>



</html>