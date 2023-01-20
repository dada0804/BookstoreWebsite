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
	private HttpServletResponse response;
	private HttpServletRequest request;
	

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		
		userDAO = new UserDAO();
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
		CommonUtility.forwardToPage(listPage,request, response);
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
			String message = "Could not create Use. A user with email " + email + " already exists.";
			CommonUtility.showMessageBackend(message, request, response);
			
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
		String destPage = "user_form.jsp";
		if(user == null) {
			destPage = "message.jsp";
			String messageString = "Could not find user with userId " + userId; 
			request.setAttribute("message", messageString);
		} else {
//			System.out.println(user.getFullName());
			request.setAttribute("user", user);
			
		}
		
		CommonUtility.forwardToPage(destPage, request, response);
	
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
			CommonUtility.showMessageBackend(message1, request, response);
		} else {
		
		Users user = new Users(email,fullname,password);
		userDAO.update(user);  
		String message = "User has been updated successfully";
		listUsers(message);
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(request.getParameter("id"));
		Users user = userDAO.get(userId);
		if(userId == 1) 
		{
			String message = "The default admin user account cannot be deleted.";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else 
			if(user == null) 
		{
			String message = "User with userId " + userId + " has been deleted by other admins.";
			CommonUtility.showMessageBackend(message, request, response);

		}
		else 
		{
		userDAO.delete(userId);
		String message = "User has been deleted successfully";
		listUsers(message);
	}
	
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean loginResult = userDAO.checkLogin(email, password);
		if(loginResult) {
			System.out.println("User is authenticated");
			request.getSession().setAttribute("useremail", email);
			String page = "/admin/";
			CommonUtility.forwardToPage(page, request, response);
		} else {
			String message = "Login Failed";
			request.setAttribute("message", message);
			String page = "login.jsp";
			CommonUtility.forwardToPage(page, request, response);

		}
		
	}
}
