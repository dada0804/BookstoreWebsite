<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internal Server Error</title>
</head>
<body>
	<div align ="center">
		<div>
			<img src = "${pageContext.request.contextPath}/images/BookstoreLogo.png"/></div>
		<div>
		<div></div> 
			<h2> Sorry, the server has encountered an error while fulfilling your request.</h2>
			<h3>Please check later or contact our admin. </h3>
		</div>
		<div>
			<a href="javascript:history.go(-1);">Go Back</a>
		</div>
	</div>
</body>
</html>