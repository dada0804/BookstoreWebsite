package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

/**
 * Servlet implementation class AddBookToOrderServlet
 */
@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookToOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("bookId"));

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));;
		System.out.println(1);
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		System.out.println(2);

		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
	    Set<OrderDetail> orderDetails = order.getOrderDetails();
	    OrderDetail orderDetail = new OrderDetail();
		System.out.println(3);

	    orderDetail.setBook(book);
	    orderDetail.setQuantity(quantity);
	    orderDetail.setBookOrder(order);
	    orderDetail.setSubtotal();
	    orderDetails.add(orderDetail);
	    order.setOrderDetails(orderDetails);
	    order.setTotal();
	    OrderDAO orderDAO = new OrderDAO();
		System.out.println(4);

	    orderDAO.update(order);
		System.out.println(1);
		
	    request.setAttribute("book", book);
	    request.setAttribute("order", order);
		String page = "add_book_result.jsp";
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
