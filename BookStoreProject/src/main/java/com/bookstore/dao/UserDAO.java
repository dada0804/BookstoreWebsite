package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;import org.junit.experimental.theories.Theories;

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
	
	//✍️ 下面这三个都用到了在annotaion里定义query，这样就不用写整个语句，直接quote名字就可以
	//JpaDAO里面会用下面这行语句来进行query，并且query可以返回结果  
	//Query query = entityManager.createNamedQuery(queryName);
	//如果想加where，比如下面的findByEmail，那么要给query设置parameter，再填充进去。


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
	
	public Users findByEmail(String email) {
		 List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		 if(listUsers != null & listUsers.size() == 1) {
			 return listUsers.get(0);
		 }
		 return null;
	}


}
