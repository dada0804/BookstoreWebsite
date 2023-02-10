package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.admin.customer.ListCustomerServlet;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private CustomerDAO customerDAO; 
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		customerDAO = new CustomerDAO();
		this.request = request;
		this.response = response;
	}

	public void listCustomer(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		request.setAttribute("listCustomer", listCustomer);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String page = "customer_list.jsp";
		CommonUtility.forwardToPage(page, request, response);
	}
	
	
	public void listCustomer() throws ServletException, IOException {
		listCustomer(null);
	}

	public void create() throws ServletException, IOException {
		String email = request.getParameter("email"); 
		Customer existCustomer = customerDAO.findByEmail(email);
		if (existCustomer != null) {
			String message = "The customer cannot be created since "+email+" has existed."; 
			listCustomer(message);
			
		} else {		
			Customer customer = new Customer();
			readField(customer);
			customer.setEmail(email);
			customerDAO.create(customer);
			String message = "The customer has been created. ";
			listCustomer(message);
		}
		
	}

	public void editCustomer() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(id);
		if(customer == null) {
			String message = "This customer does not exist. It may be deleted by other admins.";
			listCustomer(message);
			return;
		} 
		request.setAttribute("customer", customer);
		String page = "customer_form.jsp";
		CommonUtility.forwardToPage(page, request, response); 
		
	}

	public void updateService() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.get(customerId);
		Customer customerByEmail = customerDAO.findByEmail(email); 
		if(existCustomer == null) {
			String message = "Cannot find customer with ID " + customerId;
			listCustomer(message);
			return;
		}
		if(customerByEmail !=null && customerByEmail.getCustomerId() != customerId) {
			String message = "Couldn't update the customer since another customer has the same email";
			listCustomer(message);
			return;
		}
		readField(existCustomer);
		existCustomer.setEmail(email);
		customerDAO.update(existCustomer);
		String message = "The customer has been updated successfully";
		listCustomer(message);
		
	} 
	
	public void readField(Customer customer){
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		customer.setFullname(fullname);
		customer.setPassword(password);
		customer.setPhone(phoneNumber);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipcode);
		customer.setCountry(country);	
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(id);
		if(customer == null) {
			String message = "This customer does not exist. It may be deleted by other admins.";
			CommonUtility.showMessageBackend(message, request, response);
			return;
		}
		ReviewDAO reviewDAO = new ReviewDAO();
		long countByReview = reviewDAO.countByCustomer(id);
		OrderDAO orderDAO = new OrderDAO();
		long countByOrder = orderDAO.countByCustomer(id);
		
		if (countByReview >= 1 || countByOrder >= 1) {
			String message = "Could not delete this customer with ID " + id + " because he/she posted " + countByReview + " reviews and placed " + countByOrder + " orders.";
			CommonUtility.showMessageBackend(message, request, response);
			return;
		}		
		customerDAO.delete(id);
		String message = "The customer has been deleted. ";
		listCustomer(message);
		
		
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		CommonUtility.forwardToPage(loginPage, request, response);
		
	}

	public void showRegister() throws ServletException, IOException {
		String registerForm = "frontend/register_form.jsp";
		CommonUtility.forwardToPage(registerForm, request, response);
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email"); 
		Customer existCustomer = customerDAO.findByEmail(email);
		if (existCustomer != null) {
			String message = "The email "+ email + " has been registered."; 
			CommonUtility.showMessageFrontend(message, request, response);			
		} else {		
			Customer customer = new Customer();
			readField(customer);
			customer.setEmail(email);
			customerDAO.create(customer);
			String message = "You have successfully registered. <br/> " + "<a href='login'>Click here </a> to log in!";
			CommonUtility.showMessageFrontend(message, request, response);	
		}
		
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Customer customer = customerDAO.checkLogin(email, password);
		String message = null;
		if (customer != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer",customer);
			Object objRedirectURL = session.getAttribute("redirectURL");
			String targetPage = null;
			if(objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showCustomerProfile();
			}
		} else {
			message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();
		}
		
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String page = "frontend/customer_profile.jsp";
		CommonUtility.forwardToPage(page, request, response);
	}

	public void showCustomerEditProfile() throws ServletException, IOException {
		String page = "frontend/edit_profile.jsp";
		CommonUtility.forwardToPage(page, request, response);
		
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		readField(customer);
		String password = request.getParameter("password");
		if(password == null) {
			password = customer.getPassword();
			customer.setPassword(password);
		}
		customerDAO.update(customer);
		showCustomerProfile();
		
	}


	
	
	

	
	
	
	
}
