package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(name = "create_user",
		urlPatterns = { "/admin/create_user" }
		)
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// 有同样的request和response也不是很好，可以直接把request和response作为field -- 看UserService class 
//		UserServices userServices = new UserServices();
//		userServices.createUser(request, response);
//		userServices.listUsers(request,response, "New user created successfully");
		
		//改成👇 
		UserServices userServices = new UserServices( request, response);
		userServices.createUser();
		
	}

}
