package com.bookstore.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

import java.io.IOException;
import java.util.List;


public class ReviewService {
	
	private ReviewDAO reviewDAO; 
	private BookDAO bookDAO;
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	public ReviewService(HttpServletRequest request, HttpServletResponse response) {
		reviewDAO = new ReviewDAO();
		bookDAO = new BookDAO();
		customerDAO = new CustomerDAO();
		this.request = request;
		this.response = response;
	}


	public void listReview(String message) throws ServletException, IOException {
		List<Review> listReview = reviewDAO.listAll();
		String listPage = "review_list.jsp";
		request.setAttribute("listReview", listReview);
		if (message !=null) {
			request.setAttribute("message", message);

		}
		CommonUtility.forwardToPage(listPage, request, response);
		
	}
	
	public void listReview() throws ServletException, IOException {
		listReview(null);
		
	}


	public void editReview() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(id);
		if (review == null) {
			String message = "The review does not exist.";
			listReview(message);
		} else {
			String editPage = "review_form.jsp";
			request.setAttribute("review", review);
			CommonUtility.forwardToPage(editPage, request, response);
		}
		
	}


	public void updateReview() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDAO.get(id);
		String message = null;
		if (review == null) {
			message = "The review does not exist.";
		} else {
			String headline = request.getParameter("headline");
			String comment = request.getParameter("comment");
			review.setHeadline(headline);
			review.setComment(comment);
			reviewDAO.update(review);
			message = "The review has been updated successfully!";
			
		}
		listReview(message);


		
	}


	public void deleteReview() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(id);
		if (review == null) {
			String message = "The review does not exist.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			reviewDAO.delete(id);
			listReview("The review has been deleted.");
		}
	}


	public void showReviewForm() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		Book book = bookDAO.get(bookId);
		request.setAttribute("book", book);
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer customerId = customer.getCustomerId();
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId);
		String targetPage = "frontend/review_form.jsp";	
		if(review != null) {
			request.setAttribute("review",review);
			targetPage = "frontend/review_info.jsp";	
		} 
		CommonUtility.forwardToPage(targetPage, request, response);	

		
	}


	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookDAO.get(bookId);
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer  rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = new Review();
		review.setBook(book);
		review.setComment(comment);
		review.setCustomer(customer);
		review.setHeadline(headline);
		review.setRating(rating);
		
		Review newReview = reviewDAO.create(review);
		String messagePage = "frontend/review_done.jsp";
		request.setAttribute("book", book);
		CommonUtility.forwardToPage(messagePage, request, response);
		
		
		
		
	}

}
