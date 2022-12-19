 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div align = "center">
<div>
<img src = "../images/BookstoreLogo.png"/></div>
<div>
Welcome, <c:out value = "${sessionScope.useremail}"/> | <a href = "Logout">Logout</a></div>
<br/><br/>

<div>
<b>
<a href = "list_users">Users</a> |
<a href = "list_category">Categories</a> |
<a href = "books">Books</a> |
<a href = "customers">Customers</a> |
<a href = "reviews">Reviews</a> |
<a href = "orders">Orders</a> 
</b>
</div>

</div>

