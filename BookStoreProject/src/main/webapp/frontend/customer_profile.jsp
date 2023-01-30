<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile -- Online Bookstore</title>
	<link rel="stylesheet" href = "css/style.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2>Welcome, ${loggedCustomer.fullname}</h2>	
</div>
<div>
<table class="form">
    	<tr>
    
    	<td align = "right">Email:     	</td>
    	
    	<td align = "left">${loggedCustomer.email}</td>
    	
    	
    	</tr>
    	<tr>
    	<td align = "right">Full Name:     	</td>
    	<td align = "left">${loggedCustomer.fullname}</td>
    	
    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Phone:     	</td>
    	<td align = "left">${loggedCustomer.phone}</td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Address:     	</td>
    	<td align = "left">${loggedCustomer.address}</td> 	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">City:     	</td>
    	<td align = "left">${loggedCustomer.city}</td> 	    	
    	</tr>
    	
    
    	<tr>
    	<td align = "right">Zip Code:     	</td>
    	<td align = "left">${loggedCustomer.zipcode}</td>   	    	
    	</tr>
    	
    	<tr>
    	<td align = "right">Country:     	</td>
    	<td align = "left">${loggedCustomer.country}</td>   	    	
    	</tr>
    	
    	</table>
</div>

<div align = "center">
		<h5><a href = "edit_profile">Edit My Profile</a></h5>
	<%@ include file = "footer.jsp" %>
	</div>
</body>
</html>