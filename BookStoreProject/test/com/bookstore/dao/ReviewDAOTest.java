package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;
import java.util.List;

public class ReviewDAOTest {
	
	private static ReviewDAO reviewDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 reviewDAO = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}

	@Test
	public void testGet() {
		Review review = reviewDAO.get(16);
		assertEquals(review.getHeadline(), "Awesome");
	}
	
	@Test
	public void testGetFail() {
		Review review = reviewDAO.get(1);
		assertNull(review);
	}

	@Test
	public void testDeleteObject() {
		reviewDAO.delete(16);
		Review review = reviewDAO.get(16);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> reviews = reviewDAO.listAll();
		for (Review review : reviews) {
			System.out.println(review.getReviewId() + " - "+review.getBook().getTitle()
					+" - " + review.getCustomer().getFullname() 
					+" - "+ review.getHeadline() + " - " + review.getRating());
		}
		
		assertFalse(reviews.isEmpty());
	}

	@Test
	public void testCount() {
		long cnt = reviewDAO.count();
		assertTrue(cnt ==2);
	}

	@Test
	public void testCreate() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(34);
		Customer customer = new Customer();
		customer.setCustomerId(13); // make sure it exists in the database 
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Awesome");
		review.setRating(4);
		review.setComment("Beyond words");
		
		Review created = reviewDAO.create(review);
		assertTrue(created.getReviewId()>0);
		
	}
	
	@Test
	public void testSecondCreate() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(42);
		Customer customer = new Customer();
		customer.setCustomerId(16); // make sure it exists in the database 
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Not so bad");
		review.setRating(3);
		review.setComment("Learned something");
		
		Review review2 = new Review();
		Customer customer2 = new Customer();
		customer2.setCustomerId(13); // make sure it exists in the database 
		
		review2.setCustomer(customer2);
		review2.setBook(book);

		review2.setHeadline("Read it!!!");
		review2.setRating(5);
		review2.setComment("Excellent");
		
		Review created = reviewDAO.create(review);
		Review created2 = reviewDAO.create(review2);
		assertTrue(created2.getReviewId()>0);
		
	}

	@Test
	public void testUpdate() {
		Review review = reviewDAO.get(16);
		review.setHeadline("Awful");
		Review updatedReview = reviewDAO.update(review);
		assertTrue(updatedReview.getRating()==4);
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindByCustomerAndBook() {
		Integer customerId = 13;
		Integer bookId = 42; 
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId);
		assertTrue(review.getHeadline().equals("Read it!!!"));
	}
	
	@Test
	public void testFindByCustomerAndBookFail() {
		Integer customerId = 13;
		Integer bookId = 48; 
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId);
		assertNull(review);
	}
	
	@Test
	public void testListMostRecentReviews() {
		List<Review> reviews = reviewDAO.listMostRecentReviews();
		assertTrue(reviews.size() == 3);
	}

}
