package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review get(Object id) {
		return super.find(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Review.class, id);
		
	}
	
	@Override
	public Review update(Review review) {
		review.setReviewTime(new Date());
		return super.update(review);
	}

	@Override
	public List<Review> listAll() {
		List<Review> reviews = super.findWithNamedQuery("Review.findAll");
		return reviews;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public long countByBook(int id) {
		return super.countWithNamedQuery("Review.countByBook", "bookId", id);
	}
	
	public long countByCustomer(int id) {
		return super.countWithNamedQuery("Review.countByCustomer", "customerId", id);
	}

	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("customerId",customerId);
		parameters.put("bookId", bookId);
		List<Review> reviews = super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);
		if (reviews.isEmpty()) {
			return null;
		}
		return reviews.get(0);
		
	}

	public List<Review> listMostRecentReviews(){
		return super.findWithNamedQuery("Review.findAll", 0, 3);
	}


	
	
	

}
