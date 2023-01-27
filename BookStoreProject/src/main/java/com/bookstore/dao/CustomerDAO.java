package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer>{

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		
		return super.create(customer);
	}
	
	
	@Override
	public Customer get(Object id) {
		return super.find(Customer.class,id);
	}
	

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
		
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}
	
	public Customer findByEmail(String email) {
		List<Customer> customers =  super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if(!customers.isEmpty()) {
			return customers.get(0);
		}
		return null;
	}

	

	@Override
	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		return super.update(customer);
	}

	@Override
	public Customer find(Class<Customer> type, Object id) {
		// TODO Auto-generated method stub
		return super.find(type, id);
	}
	
	public Customer checkLogin(String email, String password) {
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("email",email);
		parameters.put("password", password);
		List<Customer> result =  super.findWithNamedQuery("Customer.checkLogin", parameters);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	

	@Override
	public List<Customer> findWithNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName);
	}

	@Override
	public List<Customer> findWithNamedQuery(String queryName, int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName, firstResult, maxResult);
	}

	@Override
	public List<Customer> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName, paramName, paramValue);
	}

	@Override
	public List<Customer> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery(queryName, parameters);
	}

	

	

}
