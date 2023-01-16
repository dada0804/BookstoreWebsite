package com.bookstore.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}


	@Override
	public List<Book> listAll() {		
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}

	@Override
	public Book create(Book b) {
		b.setLastUpdateTime(new Date());
		return super.create(b);
	}

	@Override
	public Book update(Book b) {
		b.setLastUpdateTime(new Date());
		return super.update(b);
	}

	@Override
	public Book find(Class<Book> type, Object id) {
		return super.find(type, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Book.class, id);
	}

	
	public Book findByTitle(String title) {
		List<Book> result = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		
		return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
	}
	
	public List<Book> listNewBooks(){
//		return super.findWithNamedQuery("Book.listNew");
		Query query = entityManager.createNamedQuery("Book.listNew");
		query.setFirstResult(0);
		query.setMaxResults(4);
		return query.getResultList();
		
	}
	
	public List<Book> search(String keyword){
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}


}
