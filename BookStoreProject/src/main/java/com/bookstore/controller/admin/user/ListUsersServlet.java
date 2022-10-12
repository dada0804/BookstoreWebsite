package com.bookstore.controller.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.UserServices;

import java.util.List;


/**
 * Servlet implementation class ListUsersServlet
 */
@WebServlet(name = "list_users", urlPatterns = {"/admin/list_users"})
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListUsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("success");
		UserServices userServices = new UserServices(request, response);
		
		// 但是这里放null不好，最好可以overload -- 看UserService class
		userServices.listUsers();

	}


}
