<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Review</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
	<%@ include file="header.jsp"%>
	<div align="center">
		<h2 class="pageheading">
			
	Edit review

		</h2>



	</div>


	<div align="center">
			<form id="reviewForm" action="update_review" method="post"">
				<input type="hidden" name="reviewId" value="${review.reviewId}">

	

		<table class="form">
			<tr>

				<td align="right">Book:</td>

				<td align="left">${review.book.title}</td>


			</tr>
			<tr>
				<td align="right">Rating: </td>
				<td align="left">${review.rating}</td>


			</tr>

			<tr>
				<td align="right">Customer:</td>
				<td align="left">${review.customer.fullname}</td>
			</tr>

			<tr>
				<td align="right">Headline: </td>
				<td align="left"><input type="text" name="headline"
					id="headline" size="20" value="${review.headline}" /></td>
			</tr>

			<tr>
				<td align="right">Comment:</td>
				<td align="left"><input type="text" name="comment" id="comment"
					size="20" value="${review.comment}" /></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">

					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<input type = "button" id="buttonCancel" value = "Cancel"/>
				</td>

			</tr>
		</table>
		</form>

	</div>
	<%@ include file="footer.jsp"%>


	<script type="text/javascript">
$(document).ready(function(){
	$("#reviewForm").validate({
		rules:{
			headline: "required",
			comment:"required",
		},
		messages:{
			headline:"Please enter the headline.",
			comment: "Please enter your comment."
		}
	});
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});

</script>

</body>

</html>