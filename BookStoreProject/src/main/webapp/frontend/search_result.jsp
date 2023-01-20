<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result for ${keyword} - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<br />
	<br />
	<%@ include file="header.jsp"%>

	<div align="center">
		<c:if test="${fn:length(result) == 0 }">
			<h2>No results for "${keyword}</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0 }">
			<h2>Result for "${keyword}":</h2>		
			<div class = "book-group">
				<c:forEach items="${result}" var="book">
					<div>
						<div id="search-image" >
							<div >
								<a href="view_book?id=${book.bookId }"> 
								<img class = "book-small"
									src="data:image/jpeg;base64,${book.base64Image}" >
								</a>
							</div>
						</div>
						<div id = "search-description" >
							<div>
								<h2><a href="view_book?id=${book.bookId }">	 <b>${book.title}</b></a></h2>
							
							</div>
							<div>Rating *****</div>
							<div>
								<i>${book.author}</i>
							</div>
							<div>
								<p>${fn:substring(book.description,0,2)}...</p>
							</div>
							</div>
							<div id = "search-price">
								<h3><b>$${book.price }</b></h3>
								<a href = "">Add to cart </a>
							</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<%@ include file="footer.jsp"%>
	<br />
	<br />
</body>
</html>