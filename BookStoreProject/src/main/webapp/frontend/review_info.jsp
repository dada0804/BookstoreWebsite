<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write a Review</title>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

<!-- 以上注意先后顺序！ -->
</head>
<body>
	<%@ include file="header.jsp"%>

	<div align="center">
		<form method="post" id="reviewForm" action="submit_review">
			<table class="normal" width="60%">
				<tr>
					<td colspan = "2"><h3>Your already wrote a review for this book.</h3></td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td><span id="book-title">${book.title }</span><br /> <br />
						<img src="data:image/jpg;base64, ${book.base64Image}"
						class="book-large" /></td>
					<td>
						<div id="rateYo"></div> 
						<br/>
						 <input type="text"
						name="headline" size="60"
						readonly="readonly"  value = "${review.headline}"/> <br /> <br />
						<textarea name="comment" cols="70" rows="10"
							readonly="readonly"  >${review.comment}</textarea>

					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<button type="submit">Submit</button> &nbsp;&nbsp;&nbsp;
						<button id="buttonCancel">Cancel</button>


					</td>

				</tr>

			</table>

		</form>
	</div>
	<%@ include file="footer.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#reviewForm").validate({
				rules : {
					headline : "required",
					comment : "required",
				},
				messages : {
					headline : "Please enter headline",
					comment : "Please enter comment"
				}
			});
			$("#rateYo").rateYo({
				rating: ${review.rating},
				readOnly:true,
				
			});

			$("#buttonCancel").click(function() {
				history.go(-1);
			});

		});
	</script>

</body>

</html>