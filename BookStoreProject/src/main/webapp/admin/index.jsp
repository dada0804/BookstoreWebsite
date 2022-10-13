<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel = "stylesheet" href = "src/main/webapp/css/style.css">
</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2>Administration Dashboard</h2>
	


</div>
    <div align = "center">
    <hr width = "60%"/>
    <h2> Quick Action</h2>
    <b>
    <a href = "create_book">New Book</a> &nbsp;
    <a href = "create_user">New User</a> &nbsp;
    <a href = "create_category">New Category</a> &nbsp;
    <a href = "create_customer">New Customer</a> &nbsp;
    </b>
        <hr width = "60%"/>
    
    
    <div align = "center">
    	<h2>Recent Sales</h2>
    
    </div>
    
            <hr width = "60%"/>
    
       <div align = "center">
    	<h2>Recent Reviews</h2>
            <hr width = "60%"/>
    
    </div>   <div align = "center">
    	<h2>Statistics</h2>        <hr width = "60%"/>
    	
    
    </div>
    
    
    
    
	</div>


	<%@ include file = "footer.jsp" %>
	
</body>
</html>