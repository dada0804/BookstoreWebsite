package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	
	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
		this.request = request;
		this.response = response;
	
	}
	public void listBook( ) throws ServletException, IOException {
		listBook(null);
	}
	
	public void listBook(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBook", listBooks);
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("book_list.jsp").forward(request,response);
	}
	
	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request,response);
	}

	public void createBook() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		Book existBook = bookDAO.findByTitle(title);
		if (existBook != null) {
			String message = "Could not create new book because the title " +  title + " has existed";
			listBook(message);
			return; 
		}
		
		
//		String author = request.getParameter("author");
//		String description = request.getParameter("description");
//		String isbn = request.getParameter("ISBN");
//		float price = Float.parseFloat(request.getParameter("price"));
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Date publishDate = null; 
//		try {
//			 publishDate =dateFormat.parse(request.getParameter("publishDate"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
//		}
		//test 
//		System.out.println("category:" + categoryId);
//		System.out.println("title:" + title);
//		System.out.println("category:" + categoryId);
//		System.out.println("author:" + author);
//		System.out.println("description:" + description);
//		System.out.println("isbn:" + isbn);
//		System.out.println("price:" + price);
//		System.out.println("publish date:" + publishDate);
		
		Book newBook = new Book();
		readBookFields(newBook);
//		newBook.setTitle(title);
//		newBook.setAuthor(author);
//		newBook.setIsbn(isbn);
//		newBook.setDescription(description);
//		newBook.setPublishDate(publishDate);
//		Category category = categoryDAO.get(categoryId);
//		newBook.setCategory(category);
//
//		Part part = request.getPart("bookImage");
//		if(part != null &&part.getSize()>0) {
//			long size = part.getSize();
//			byte[] imageBytes = new byte[(int) size];
//			InputStream inputStream = part.getInputStream();
//			inputStream.read(imageBytes);
//			inputStream.close();
//			
//			newBook.setImage(imageBytes);
//		}
		
		Book createdBook = bookDAO.create(newBook);
		
		if(createdBook.getBookId()>0) {
			String message = "A book has been created successfully"; 
//			request.setAttribute("message", message);
			listBook(message);
			
		}
		
	}
	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		if(book == null) {
			String message = "Could not find book with ID " + bookId;
			listBook(message);
			return;
		}
		List <Category> listCategories = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategories);
		request.setAttribute("book", book);
		String editPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	public void updateBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);
		
		if(existBook == null) {
			String message = "Could not find book with ID " + bookId;
			listBook(message);
			
		}
		
		if(bookByTitle == null | existBook.equals(bookByTitle)) {
			readBookFields(existBook);
			bookDAO.update(existBook);
			String message = "The book has been updated sucessfully";
			listBook(message);
			

		}
		
		else  {
			String message = "Couldn't update book because there's another book having same title.";
			listBook(message);
			return;
		}
		
		
		

	}
	
	public void readBookFields(Book book) throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");		
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("ISBN");
		float price = Float.parseFloat(request.getParameter("price"));
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null; 
		try {
			 publishDate =dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setDescription(description);
		book.setPublishDate(publishDate);
		book.setPrice(price);
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);

		Part part = request.getPart("bookImage");
		if(part != null &&part.getSize()>0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			book.setImage(imageBytes);
		}
		
	}
	public void deleteBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		String msg = null;
		if(book == null) {
			msg = "Couldn't find the book id " + bookId + " or it might be deleted by other admin.";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("message.jsp").forward(request,response);
		} else {
			bookDAO.delete(bookId);
			msg = "The book has been deleted successfully";
			listBook(msg);
		}
		
		
		
	}
}
