package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;


@WebServlet("") //handle request from homepage 
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HomeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		BookDAO bookDAO = new BookDAO();
		
//		listCategories.forEach(c->System.out.println(c.getName()));			
		List<Book> listNewBooks = bookDAO.listNewBooks();
		List<Book> listBestSellers = bookDAO.listBestSellers();
		List<Book> listMostFavoredBooks = bookDAO.listMostFavoredBooks();
		
		request.setAttribute("listNewBook", listNewBooks);
		request.setAttribute("listBestSellers", listBestSellers);
		request.setAttribute("mostFavoredBooks", listMostFavoredBooks);

		
		String homePage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
		dispatcher.forward(request, response);
	}

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
