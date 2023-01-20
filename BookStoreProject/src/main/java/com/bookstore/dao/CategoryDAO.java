package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;
import com.mysql.cj.Query;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Category create(Category category) {
		return (Category) super.create(category);
	}

	@Override
	public Category get(Object categoryId) {
		// TODO Auto-generated method stub
		return super.find(Category.class, categoryId);
	}
	
	@Override
	public Category update(Category category) {
		return (Category) super.update(category);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Category.class, id);
		
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("category.findAll");
	}
	

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("category.countAll");
	}
	
	public Category findByname(String name) {
		List<Category> listCategory = super.findWithNamedQuery("category.findByName", "name", name);
		listCategory.forEach(c->System.out.println(c.getName()));
		if(listCategory != null && listCategory.size() == 1) {
			return listCategory.get(0);
		}
		return null;
	}
	
	

}
