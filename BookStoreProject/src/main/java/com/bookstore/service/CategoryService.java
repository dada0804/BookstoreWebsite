package com.bookstore.service;

import java.awt.im.InputMethodRequests;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;



public class CategoryService {
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	
	public CategoryService(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		categoryDAO = new CategoryDAO(entityManager);
		this.request = request;
		this.response = response;
	
	}
	
	public void listCategory(String message) throws  IOException, ServletException {
		List<Category> listCategories = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategories);
		request.setAttribute("message", message);
		request.getRequestDispatcher("category_list.jsp").forward(request, response);
	}
	
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("The name is " + name);
		Category category = categoryDAO.findByname(name);
		if(!testExist(category, "the category " + name + " has existed" )){
			Category cat  = new Category(name); 
			categoryDAO.create(cat);
		listCategory("New Category Created Successfully");}
			
	}

	public void editCategory() throws ServletException, IOException {
		//edit只是跳转到form页面 
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Category category = categoryDAO.get(id); 
		String destPage = "category_form.jsp";
		if(category == null) {
			String msg = "The category has been deleted by other admins.";
			destPage = "message.jsp";
		} else {
//			Category categoryNew = new Category(id,name);
			request.setAttribute("category",category);
		}
		request.getRequestDispatcher(destPage).forward(request, response);		
	}

	public void updateCategory() throws IOException, ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
//		Category testCategory = categoryDAO.findByname("name");
		Category category = categoryDAO.findByname(name);

		if(!testExist(category, "The category has already existed")) {
			Category categoryNew = new Category(id, name);
			categoryDAO.update(categoryNew);
			listCategory("The category has been updated successfully");
		}
		
	}
	
	
	
	
	
	private boolean testExist(Category category, String msg) throws ServletException, IOException {
		if(category != null) {
			request.setAttribute("message", msg);
			request.getRequestDispatcher("message.jsp").forward(request, response);		
			return true;
		}
		return false;	
	}

	public void deleteCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(id);
		if(category == null) {
			String msg = "This category may be deleted by other admins";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			categoryDAO.delete(id);
			listCategory("The category has been deleted successfully");
		}
		
	}
	
	
	
	

}
