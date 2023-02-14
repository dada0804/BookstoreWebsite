package com.bookstore.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
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
	public void showCheckoutForm() throws ServletException, IOException {
		
		String page = "/frontend/checkout.jsp";
		CommonUtility.forwardToPage(page, request, response);
	}
	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String streetAddress = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		String shippingAddress= streetAddress + " , " + city + " , " + zipCode + " , " + country;
		String paymentMethod = request.getParameter("paymentMethod");
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);	
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();
		Iterator<Book> iterator = items.keySet().iterator();
		Set<OrderDetail> orderDetails = new HashSet<>();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer qty = items.get(book);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(qty);
			orderDetail.setSubtotal(book.getPrice()*qty);
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal();
		orderDAO.create(order);
		request.getSession().setAttribute("cart", null);;
		String message = "Thank You! Your order has been received. We will deliver your books within a few days.";
		CommonUtility.showMessageFrontend(message, request, response);
	}
	public void viewOrder() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer customerId = customer.getCustomerId();
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		request.setAttribute("listOrder", listOrders);
		String page = "/frontend/order_list.jsp";
		CommonUtility.forwardToPage(page, request, response);
		
	}
	public void showOrderDetail() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id")); 
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer customerId = customer.getCustomerId();
		BookOrder bookOrder = orderDAO.get(orderId, customerId);
		request.setAttribute("order", bookOrder);
		String page = "/frontend/order_detail.jsp";
		CommonUtility.forwardToPage(page, request, response);
		
	}
	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder bookOrder = orderDAO.get(orderId);
		if(bookOrder == null) {
			String message = "Could not find order with ID " + orderId ;
			listOrders(message);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("order", bookOrder);
		String page = "order_form.jsp";
		CommonUtility.forwardToPage(page, request, response);
		
	}
	public void updateOrder() throws ServletException, IOException {
		BookOrder order = (BookOrder) request.getSession().getAttribute("order");
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String paymentMethod = request.getParameter("paymentMethod");
		String orderStatus = request.getParameter("orderStatus");
		
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(address);
		order.setPaymentMethod(paymentMethod);
		order.setStatus(orderStatus);
		
		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];
		for (int i = 0; i < arrayBookId.length; i++) {
			arrayQuantity[i] = request.getParameter("quantity"+(i+1));
		}
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();
		for(int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);
			OrderDetail orderDetail  = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			System.out.println(orderDetail.getBook().getPrice());
			float subtotal = price * quantity;
			orderDetail.setSubtotal(subtotal);
			orderDetails.add(orderDetail);
		}
		order.setOrderDetails(orderDetails);
		order.setTotal();
		
		orderDAO.update(order); 
		
		String message = "The order " + order.getOrderId() + " has been udpated successfully!";
		
		listOrders(message);
		
	}
	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDAO.get(orderId);
		String message = null;
		if(order == null) {
			message = "The order " + orderId + " does not exist.";
		} else {
		orderDAO.delete(orderId);
		message = "The order " + orderId + " has been deleted successfully.";}
		listOrders(message);
		
	}
	
	
	
	
	

}
