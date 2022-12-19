package com.bookstore.dao;

import java.util.List;

public interface GenericDAO<T> {
	public T create(T t);
	T update(T t);
	T get(Object id);
	void delete(Object id);
	List<T> listAll();
	long count();
	

}
