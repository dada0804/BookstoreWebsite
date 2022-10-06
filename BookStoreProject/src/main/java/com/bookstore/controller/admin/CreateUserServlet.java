package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringCache;

import com.bookstore.service.UserServices;

@WebServlet({ "/CreateUserServlet", "/admin/create_user" })
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");

		String password = request.getParameter("password");

		response.getWriter().println("Email: " + email);
		response.getWriter().println("fullname: " + fullname);
		response.getWriter().println("password: " + password);
		
		UserServices userServices = new UserServices();
		userServices.createUser(email, fullname,password);

		
		
	}

}
