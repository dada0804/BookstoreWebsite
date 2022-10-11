package com.bookstore.service;

import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
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
	
	public void createUser() throws IOException, ServletException
	{
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");

		String password = request.getParameter("password");	
		System.out.println("the email is "+email+ " and the name is " + fullname+ " and the pw is " +password);
		
		//check whether the email already exists 
		Users existUsers = userDAO.findByEmail(email);
		if (existUsers != null) {
			String message = "Could not create Use. A user with email " + email + "already exists.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		} else {
			Users newUser = new Users(email, fullname, password);
			System.out.println("is this okay");
			userDAO.create(newUser);
			listUsers( "New user created successfully");

		}
		

		
		
	}

	public void editUser() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(request.getParameter("id"));
		Users user = userDAO.get(userId);
		if(user == null) {
			String messageString = "Could not find user with userId " + userId; 
			request.setAttribute("message", messageString);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println(user.getFullName());
			String editPage = "user_form.jsp";
			request.setAttribute("user", user);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
			requestDispatcher.forward(request, response);
		}
	
	}
	
	public void updateUser() throws ServletException, IOException {
		Long userId = Long.parseLong(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");	
		
		System.out.println("the userId is " + userId);
		Users userByEmail = userDAO.findByEmail(email);
		Users userById = userDAO.get(userId);
		//此时还没更新 
		if(userByEmail !=null && userByEmail.getUserId() != userById.getUserId()) {
			String message1 = "Couldn't update the user. The email already existed";
			request.setAttribute("message", message1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
		
		Users user = new Users(userId,email,fullname,password);
		userDAO.update(user);  
		String message = "User has been updated successfully";
		listUsers(message);
		}
		
	}
	

}
