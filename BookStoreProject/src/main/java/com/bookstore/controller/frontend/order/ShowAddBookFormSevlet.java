package com.bookstore.controller.frontend.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.service.OrderService;

/**
 * Servlet implementation class ShowAddBookFormSevlet
 */
@WebServlet("/admin/add_book_form")
public class ShowAddBookFormSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAddBookFormSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDao = new BookDAO();
		List<Book> listBook = bookDao.listAll();
		request.setAttribute("listBook", listBook);
		request.getRequestDispatcher("add_book_form.jsp").forward(request, response);
	}

}
