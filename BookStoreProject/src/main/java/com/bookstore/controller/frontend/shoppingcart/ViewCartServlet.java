package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

/**
 * Servlet implementation class ViewCartServlet
 */
@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object cartObject = request.getSession().getAttribute("cart");
		if(cartObject == null) {
			ShoppingCart shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		//Test
//		Book book = new Book();
//		book.setTitle("Effective Java");
//		book.setPrice(20);

//		
//		BookDAO bookDAO = new BookDAO();
//		Book book = bookDAO.get(48);
//		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
//		shoppingCart.addItem(book);		
//		shoppingCart.addItem(book);
//		shoppingCart.addItem(bookDAO.get(34));
//		shoppingCart.addItem(bookDAO.get(42));
//		
		String  cartPage = "frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cartPage);
		dispatcher.forward(request, response);
		
	}

}
