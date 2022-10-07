package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	
	private UserDAO userDAO;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private HttpServletResponse response;
	private HttpServletRequest request;
	

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		
		userDAO = new UserDAO(entityManager);
		this.response = response;
		this.request = request; 
	}
	
	public void listUsers() throws ServletException, IOException {
		listUsers (null);
	}
	
	public void listUsers(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
	
		request.setAttribute("listUsers", listUsers);
		
		System.out.println("success");
		
		//如果是在create user里进入的listUser 就会有message 
		//如果是直接点user的话 就没有message 
		//所以要把message放在create user这个func里 
		if(message !=null) {
			request.setAttribute("message", message);
		}
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		
		requestDispatcher.forward(request, response);
	}
	
	public void createUser() throws IOException
	{
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");

		String password = request.getParameter("password");	
		System.out.println("the email is "+email+ " and the name is " + fullname+ " and the pw is " +password);

//		response.getWriter().println("Email: " + email);
//		response.getWriter().println("fullname: " + fullname);
//		response.getWriter().println("password: " + password);
		Users newUser = new Users(email, fullname, password);
		System.out.println("is this okay");
		userDAO.create(newUser);
		
	}
	

}
