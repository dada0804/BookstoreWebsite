package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

/**
 * Servlet implementation class RemoveBookFromCartServlet
 */
@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RemoveBookFromCartServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
//		BookDAO bookDAO = new BookDAO();
//		Book book = bookDAO.get(bookId);
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
//		cart.removeItem(book);
		cart.removeItem(new Book(bookId));
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
		
		
	}

}
