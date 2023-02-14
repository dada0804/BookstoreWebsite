<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<br />
	<br />
	<%@ include file="header.jsp"%>

	<div class="center">
		<h3>This is main content:</h3>
		<h2>New books</h2>
		<div>
			<c:forEach items="${listNewBook}" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId }"> <img
							src="data:image/jpeg;base64,${book.base64Image}"
							class="book-small">
						</a>
					</div>
					<div><a href="view_book?id=${book.bookId }">
						
							<b>${book.title}</b>
					</a>
				</div>
				<div>
					<%@ include file="book_rating.jsp"%>


				</div>
				<div>
					<i>${book.author}</i>
				</div>
				<div>
					<b>${book.price }</b>
				</div>
				</div>
			</c:forEach>

		</div>
	</div>
	<div class="next-row center">
		<h2>Best Selling books</h2>
		<div>
			<c:forEach items="${listBestSellers}" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId }"> <img
							src="data:image/jpeg;base64,${book.base64Image}"
							class="book-small">
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId }"> <b>${book.title}</b>
						</a>
					</div>
					<div>
						<%@ include file="book_rating.jsp"%>


					</div>
					<div>
						<i>${book.author}</i>
					</div>
					<div>
						<b>${book.price }</b>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
	<div class="next-row center">
		<h2>Most-favoured books</h2>
		<div>
			<c:forEach items="${mostFavoredBooks}" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId }"> <img
							src="data:image/jpeg;base64,${book.base64Image}"
							class="book-small">
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId }"> <b>${book.title}</b>
						</a>
					</div>
					<div>
						<%@ include file="book_rating.jsp"%>


					</div>
					<div>
						<i>${book.author}</i>
					</div>
					<div>
						<b>${book.price }</b>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>


	<%@ include file="footer.jsp"%>
	<br />
	<br />
</body>
</html>