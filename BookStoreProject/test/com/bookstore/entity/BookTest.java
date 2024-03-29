package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAverageRating1() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		book.setReviews(reviews);
		float averageRating = book.getAverageRating();
		assertTrue(averageRating == 5.0);
	}
	
	@Test
	public void testAverageRating2() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		Review review2 = new Review();
		review2.setRating(1);
		reviews.add(review2);
		book.setReviews(reviews);
		float averageRating = book.getAverageRating();
		assertTrue(averageRating == 3.0);
	}
	
	@Test
	public void testAverageString() {
		Book book = new Book();
		float averageRating = 0.0f;
		String ratingString = book.getRatingString(averageRating);
		System.out.println(ratingString);
		String expecteString = "off,off,off,off,off";
		assertTrue(ratingString.equals(expecteString));
	}
	
	

}
