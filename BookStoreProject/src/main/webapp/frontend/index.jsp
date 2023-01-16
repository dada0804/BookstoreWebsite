<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Books - Online Books Store</title>
	<link rel="stylesheet" href = "css/style.css">

</head>
<body>
	<br/><br/>
	<%@ include file  = "header.jsp"%>
	
	<div align = "center">
	<h3>This is main content:  </h3>
	<h2>New books</h2>	
		<div align = "center" style = "margin : 0 auto">
			<c:forEach items = "${listNewBook}" var = "book">
				<div style = "display: inline-block; margin: 20px">
					<div>
						<a href = "view_book?id=${book.bookId }">
							<img src = "data:image/jpeg;base64,${book.base64Image}" width="128" height="164">
						</a>
					</div>
						<a href = "view_book?id=${book.bookId }">
							<div><b>${book.title}</b>
						</a>
					</div>
					<div>Rating *****</div>
					<div><i>${book.author}</i></div>
					<div><b>${book.price }</b></b></div>
				</div>
			</c:forEach>
		
		</div>
	<div align = "center" style = "clear: both">
	<h2>Best Selling books</h2>
	<h2>Most-favoured books</h2>
	</div>	
	</div>
		
	<%@ include file  = "footer.jsp"%>
<br/><br/>
</body>
</html>