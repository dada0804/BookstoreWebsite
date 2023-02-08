package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateCartServlet
 */
@WebServlet("/update_cart")
public class updateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		String[] arrayBookIds = request.getParameterValues("bookId");
		String[] arrayQuantities = new String[arrayBookIds.length];
		for(int i = 1; i <= arrayQuantities.length; i++) {
			String aQuantity = request.getParameter("quantity"+i);
			arrayQuantities[i-1] = aQuantity;
		}
		
//		test
//		response.getWriter().println(Arrays.asList(arrayBookIds));
//		response.getWriter().println(Arrays.asList(arrayQuantities));
		
		int[] bookIds = Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
		shoppingCart.updateCart(bookIds, quantities);
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);


	}

}
