 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div align = "center">
<div>
	<a href = "${pageContext.request.contextPath}/admin/">
	<img src = "../images/BookstoreLogo.png"/></a></div>
<div>
Welcome, <c:out value = "${sessionScope.useremail}"/> | <a href = "logout">Logout</a>
<br/><br/>
</div>

<div id="headermenu" >
	<div >
		<a href = "list_users">
			<img src = "../images/user.png" class="menu_item"/><br/>Users
		</a> 
			</div>
	<div class="menu_item">
		<a href = "list_category">
			<img src = "../images/category.png" class="menu_item"/><br/>Categories
		</a> 
	</div>
	<div>
		<a href = "list_books">
			<img src = "../images/book.png" class="menu_item"/><br/>Books
		</a> 
	</div>
	<div >
		<a href = "customers">
			<img src = "../images/customer.png" class="menu_item"/><br/>Customers
		</a> 
	</div>
	<div >
		<a href = "reviews">
			<img src = "../images/review.png" class="menu_item"/><br/>Reviews
		</a> 
	</div>
	<div >
		<a href = "orders">
			<img src = "../images/order.png" class="menu_item"/><br/>Orders
		</a> 
	</div>

</div>

