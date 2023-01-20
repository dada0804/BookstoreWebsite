<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title} - Online Books Store</title>
	<link rel="stylesheet" href = "css/style.css">

</head>
<body>
	<br/><br/>
	<%@ include file  = "header.jsp"%>
	
	<div class = "center" >
		<table class = "book">
			<tr>
				<td colspan = "3" align = "left" >
					<p id="book-title"> ${book.title}</p> 
					<span id = "book-author">by ${book.author }</span>
				</td>
			</tr>
			<tr>
				<td rowspan = "2" valign="top">
					<img src = "data:image/jpg;base64, ${book.base64Image}" class = "book-large" />
				</td>
			
			<td valign = "top" align = "left">
				Rating *****
			</td>
			<td valign="top" rowspan = "2" width = "20%">
				<h2></h2>$${book.price}</h2>
				<br/><br/>
				<button type = "submit"> Add to Cart</button>
			</td>
				
			</tr>
			<tr>
				<td  id = "description">
				${book.description}
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><h3>Customer Reviews</h3></td>
				<td colspan = "2" align = "center">
					<button>Write a Customer Review</button>
				</td>
			</tr>
		</table>
		
		
	</div>
		
	<%@ include file  = "footer.jsp"%>
<br/><br/>
</body>
</html>