package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;


import com.bookstore.entity.Customer;
import com.sun.source.tree.AssertTree;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("eager@gmail.com");
		customer.setFullname("Tom Eager");
		customer.setCity("New York");
		customer.setCountry("USA");
		customer.setAddress("100 North Avenue");
		customer.setPassword("secret");
		customer.setPhone("100-110");
		customer.setZipcode("GEd3");
		Customer createdCustomer = customerDAO.create(customer);
		System.out.println(createdCustomer.getCustomerId());
		assertTrue(createdCustomer.getCustomerId()>0);
	}

	@Test
	public void testGet() {
		Integer customerId = 11 ; 
		Customer customer = customerDAO.get(customerId);
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDAO.get(11);
		customer.setFullname("Tom Water");
		Customer updateCustomer= customerDAO.update(customer);
		assertTrue(updateCustomer.getFullname()=="Tom Water");
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 11 ; 
		customerDAO.delete(customerId);
		Customer customer = customerDAO.get(customerId);
		assertNull(customer);
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomer = customerDAO.listAll();
		assertTrue(listCustomer.size() == 1);
	}
	
	@Test
	public void testCountAll() {
		long cnt = customerDAO.count();
		assertTrue(cnt ==1);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "eager@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		System.out.println(customer.getFullname());
	  
		assertTrue(customer.getFullname().equals("Tom Eager"));
	}
	
	@Test
	public void testCheckLogin() {
		String email = "mayan1015@gmail.com";
		String password = "lalala";
		Customer customer = customerDAO.checkLogin(email, password);
		System.out.println(customer.getFullname());
		assertTrue(customer.getFullname().equals("mayan"));
	}

}
