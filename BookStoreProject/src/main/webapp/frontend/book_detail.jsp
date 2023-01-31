<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}-OnlineBooks Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<br />
	<br />
	<%@ include file="header.jsp"%>

	<div class="left">
		<table class="book">
			<tr>
				<td colspan="3" align="left">
					<p id="book-title">${book.title}</p> <span id="book-author">by
						${book.author }</span>
				</td>
			</tr>
			<tr>
				<td rowspan="2" valign="top"><img
					src="data:image/jpg;base64, ${book.base64Image}" class="book-large" />
				</td>

				<td valign="top" align="left"><%@ include
						file="book_rating.jsp"%>
						<a href = "#reviews">${fn:length(book.reviews)} Reviews</</a>
						</td>
				<td valign="top" rowspan="2" width="20%">
					<h2></h2>$${book.price}
					</h2> <br /> <br />
					<button type="submit">Add to Cart</button>
				</td>

			</tr>
			<tr>
				<td id="description">${book.description}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><h3><a id = "reviews">Customer Reviews</a></h3></td>
				<td colspan="2" align="center">
					<button>Write a Customer Review</button>
				</td>
			</tr>

			<tr>
				<td colspan="3" align = "left">
					<table class = "normal">
						<c:forEach items="${book.reviews }" var="review">
							<tr>
								<td><c:forTokens items="${review.stars}" delims=","
										var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating_on.pic" />
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="images/rating_off.pic" />
										</c:if>
									</c:forTokens> -<b>${review.headline }</b></td>
							</tr>
							<tr>
								<td>by ${review.customer.fullname } on ${review.reviewTime}
								</td>
							</tr>
							
							<tr>
							<td>
							<i>${review.comment}</i>
							</td>
							</tr>
							
						</c:forEach>
					</table>
			</tr>

		</table>


	</div>

	<%@ include file="footer.jsp"%>
	<br />
	<br />
</body>
</html>