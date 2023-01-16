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
			<div align="left" style="width: 80%; margin: 0 auto;">
				<c:forEach items="${result}" var="book">
					<div>
						<div style="display: inline-block; margin: 20px; width:10%" >
							<div align = "left">
								<a href="view_book?id=${book.bookId }"> <img
									src="data:image/jpeg;base64,${book.base64Image}" width="128"
									height="164">
								</a>
							</div>
						</div>
						<div style="display: inline-block; margin: 20px left; vertical-align: top; width: 50%;" align = "left">
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
							<div style = "display: inline-block; margin: 20px; vertical-align: top;">
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