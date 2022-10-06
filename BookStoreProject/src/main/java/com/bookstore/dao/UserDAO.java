package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Users create(Users user) {
		return super.create(user); //change type from T to user 
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub	
		return super.update(user);
	}

	@Override
	public Users get(Object userID) {
		
		return super.find(Users.class, userID);
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
		
	}

	@Override
	public List<Users> listAll() {	
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
//		return listAll().size();
		return super.countWithNamedQuery("Users.countAll");
	}


}
