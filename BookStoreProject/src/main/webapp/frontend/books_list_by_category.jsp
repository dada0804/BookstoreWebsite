<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books in ${category.name} - Online Books Store</title>
	<link rel="stylesheet" href = "css/style.css">

</head>
<body>
	<br/><br/>
	<%@ include file  = "header.jsp"%>
	
	<div class = "center">
	<h2> ${category.name }</h2>
	</div>
	
	<div class = "book_group">
		<c:forEach items = "${listBooks}" var = "book">
			<div class= "book">
				<div>
					<a href = "view_book?id=${book.bookId }">
						<img src = "data:image/jpeg;base64,${book.base64Image}" width="128" height="164">
					</a>
				</div>
					<a href = "view_book?id=${book.bookId }">
						<div><b>${book.title}</b>
					</a>
				</div>
				<div>
								<%@ include file  = "book_rating.jsp"%>

				</div>
				<div><i>${book.author}</i></div>
				<div><b>${book.price }</b></b></div>
			</div>
		</c:forEach>
	
	</div>
	
		
	<%@ include file  = "footer.jsp"%>
<br/><br/>
</body>
</html>