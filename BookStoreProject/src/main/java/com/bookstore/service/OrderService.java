package com.bookstore.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

public class OrderService {
	
	private OrderDAO orderDAO; 
	private HttpServletRequest request;
	private HttpServletResponse response;
	public OrderService(HttpServletRequest request, HttpServletResponse response) {
		orderDAO = new OrderDAO();
		this.request = request;
		this.response = response;
	}
	public void listOrders(String message) throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		request.setAttribute("listOrder", listOrder);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String page = "order_list.jsp";
		CommonUtility.forwardToPage(page, request, response);
	}
	
	public void listOrders() throws ServletException, IOException {
		listOrders(null);
	}
	public void viewOrderDetail() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder bookOrder = orderDAO.get(orderId);
		if(bookOrder == null) {
			String message = "The order does not exist.";
			CommonUtility.showMessageBackend(message, request, response);
			return;
		}
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		request.setAttribute("order", bookOrder);
		String page = "order_detail.jsp";
		CommonUtility.forwardToPage(page, request, response);
	}
	
	
	
	
	

}
