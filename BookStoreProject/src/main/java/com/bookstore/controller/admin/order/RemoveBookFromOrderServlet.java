package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

/**
 * Servlet implementation class RemoveBookFromOrderServlet
 */
@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBookFromOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = (BookOrder) request.getSession().getAttribute("order");
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		Iterator<OrderDetail> iterator = orderDetails.iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			
			if (orderDetail.getBook().getBookId() == bookId) {
				float newTotal = order.getTotal() - orderDetail.getSubtotal();
				iterator.remove();
				order.setTotal();
			}
		}
//		order.setOrderDetails(orderDetails);
//		order.setTotal();
//		OrderDAO orderDAO = new OrderDAO();
//		orderDAO.update(order);
		
		String page = "order_form.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}

}
