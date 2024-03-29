package com.bookstore.controller.admin.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.service.CustomerServices;

/**
 * Servlet implementation class ListCustomerServlet
 */
@WebServlet("/admin/list_customer")
public class ListCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListCustomerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerService = new CustomerServices(request, response);
		customerService.listCustomer();
		
	}

}
