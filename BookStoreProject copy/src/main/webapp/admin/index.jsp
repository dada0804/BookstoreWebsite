<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel = "stylesheet" type="text/css" href = "../css/style.css">
</head>
<body>
<%@ include file = "header.jsp" %>
<div align = "center">
	<h2 class = "pageheading">Administration Dashboard</h2>
	


</div>
    <div align = "center">
    <hr width = "60%"/>
    <h1 class = "pageheading"> Quick Action</h1>
    <b>
    <a href = "create_book">New Book</a> &nbsp;
    <a href = "create_user">New User</a> &nbsp;
    <a href = "create_category">New Category</a> &nbsp;
    <a href = "create_customer">New Customer</a> &nbsp;
    </b>
        <hr width = "60%"/>
    
    
    <div align = "center">
    	<h2 class = "pageheading">Recent Sales</h2>
    
    </div>
    
            <hr width = "60%"/>
    
       <div align = "center">
    	<h2 class = "pageheading">Recent Reviews</h2>
            <hr width = "60%"/>
    
    </div>   <div align = "center">
    	<h2 class = "pageheading">Statistics</h2>        <hr width = "60%"/>
    	
    
    </div>
    
    
    
    
	</div>


	<%@ include file = "footer.jsp" %>
	
</body>
</html>