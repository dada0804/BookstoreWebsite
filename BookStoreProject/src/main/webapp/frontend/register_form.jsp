<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register As a Customer</title>
	<link rel="stylesheet" href = "css/style.css">
	<script type = "text/javascript" src="js/jquery-3.6.1.min.js"></script> 
	<script type = "text/javascript" src="js/jquery.validate.min.js"></script> 

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
<h2 class="pageheading">
	Register as a Customer
	
	
	</h2>
	

    
    </div>   
    
    
    <div align = "center">
	
    	<form id = "registerForm" action = "register_customer" method = "post" ">
	
    	
    	<table class="form">
    	<tr>
    
    	<td align = "right">Email:     	</td>
    	
    	<td align = "left"><input type = "text" name = "email" id = "email" size = "20" value = "${customer.email}"/></td>
    	
    	
    	</tr>
    	<tr>
    	<td align = "right">Full Name:     	</td>
    	<td align = "left"><input type = "text" name = "fullname" id = "fullname" size = "20" value = "${customer.fullname}" /></td>
    	
    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Password:     	</td>
    	<td align = "left"><input type = "text" name = "password" id = "password" size = "20" value = "${customer.password}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Confirm Password:     	</td>
    	<td align = "left"><input type = "text" name = "confirmPassword" id = "confirmPassword" size = "20" value = "${customer.password}"/></td> 	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Phone:     	</td>
    	<td align = "left"><input type = "text" name = "phone" id = "phone" size = "20" value = "${customer.phone}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Address:     	</td>
    	<td align = "left"><input type = "text" name = "address" id = "address" size = "20" value = "${customer.address}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">City:     	</td>
    	<td align = "left"><input type = "text" name = "city" id = "city" size = "20" value = "${customer.city}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Zip Code:     	</td>
    	<td align = "left"><input type = "text" name = "zipCode" id = "zipCode" size = "20" value = "${customer.zipcode}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Country:     	</td>
    	<td align = "left"><input type = "text" name = "country" id = "country" size = "20" value = "${customer.country}"/></td>   	    	
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
	$("#registerForm").validate({
		rules:{
			email:{
				required: true,
				email: true
			},
			fullname:"required",
			password:"required",
			confirmPassword:{
				required: true,
				equalTo: "#password"
			},
			phone:"required",
			address:"required",
			city:"required",
			zipCode:"required",
			country:"required",
			
		},
		messages:{
			email:{
				required:"Please enter email",
				email: "Please enter a valid email"
			},
			fullname:"Please enter fullname",
			password:"Please enter password",
			confirmPassword:{
				required:"Please enter confirmed password",
				equalTo:"Confirm password does not match password"
			},
			phone:"Please enter phone number",
			address:"Please enter your address",
			city:"Please enter city",
			zipCode:"Please enter zip code",
			country:"Please enter country name",
		}
	});
	
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});


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