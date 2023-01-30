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
	<h2>Welcome, ${loggedCustomer.fullname}</h2>	
</div>
    
    
    <div align = "center">
	
    	<form id = "editProfile" action = "update_profile" method = "post" ">
	
    	
    	<table class="form">
    	<tr>
    
    	<td align = "right">Email:     	</td>
    	
    	<td align = "left">${loggedCustomer.email}</td>
    	
    	
    	</tr>
    	<tr>
    	<td align = "right">Full Name:     	</td>
    	<td align = "left"><input type = "text" name = "fullname" id = "fullname" size = "20" value = "${loggedCustomer.fullname}" /></td>
    	
    	
    	</tr>
    	
    	
    	
    	<tr>
    	<td align = "right">Phone:     	</td>
    	<td align = "left"><input type = "text" name = "phone" id = "phone" size = "20" value = "${loggedCustomer.phone}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Address:     	</td>
    	<td align = "left"><input type = "text" name = "address" id = "address" size = "20" value = "${loggedCustomer.address}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">City:     	</td>
    	<td align = "left"><input type = "text" name = "city" id = "city" size = "20" value = "${loggedCustomer.city}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Zip Code:     	</td>
    	<td align = "left"><input type = "text" name = "zipCode" id = "zipCode" size = "20" value = "${loggedCustomer.zipcode}"/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Country:     	</td>
    	<td align = "left"><input type = "text" name = "country" id = "country" size = "20" value = "${loggedCustomer.country}"/></td>   	    	
    	</tr>
    	<tr>
    	<td align = "center" colspan = "2"><i>(Leave the password fields blank if you don't want to change password.)</i></td></tr>
    	<tr>
    	<td align = "right">Password:     	</td>
    	<td align = "left"><input type = "text" name = "password" id = "password" size = "20" value = ""/></td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Confirm Password:     	</td>
    	<td align = "left"><input type = "text" name = "confirmPassword" id = "confirmPassword" size = "20" value = ""/></td> 	    	
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
	$("#editProfile").validate({
		rules:{
			
			fullname:"required",
			confirmPassword:{
				equalTo: "#password"
			},
			phone:"required",
			address:"required",
			city:"required",
			zipCode:"required",
			country:"required",
			
		},
		messages:{
			
			fullname:"Please enter fullname",
			confirmPassword:{
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