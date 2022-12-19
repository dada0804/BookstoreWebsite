<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	
	<%@ include file  = "header.jsp"%>
	
	
	<div align = "center">
	<h3>Please Login in  </h3>
	<form>
		Email: <input type = "text" size = "10"><br/>
		Password: <input type = "password" size = "10"/>
		<input type = "submit" value = "Login"/>
		
		</form>
	
	</div>
	
		<%@ include file  = "footer.jsp"%>
	
</body>
</html>