
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div align = "center">
	<div>
	<img src = "images/BookstoreLogo.png"/>
	<div>
	<input type = "text" name = "keyword" size = "50"/>
	<input type = "button" value = "search"/>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="Login">Sign In </a> |
	<a href="Login">Register</a> |
	<a href="Login">Cart</a>
	
	
	</div>
	<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<c:forEach var = "category" items = "${listCategory}" varStatus = "status">
	<a href = "view_category?id=${category.categoryId}">
		<font size = "+1"><b><c:out value = "${category.name}"/></b></font></a>
		<c:if test = "${not status.last}">
		&nbsp;|&nbsp;
		</c:if>
	</c:forEach>	
	
	
	</div>
</div>