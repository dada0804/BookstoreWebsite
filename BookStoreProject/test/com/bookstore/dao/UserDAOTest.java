package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest {
	
	private static UserDAO userDAO = new UserDAO(entityManager);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager); 
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateUsers() {
		Users user1  = new Users();
		user1.setUserId(1l);
		user1.setEmail("hahala@codejava.net");
		user1.setFullName("xhhxi");
		user1.setPassword("assssdfg");	
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId()>0);	
	}	
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldNoSet() {
		Users user1  = new Users();
		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId()>0);	
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1l);
		user.setEmail("name@codejava.net");
		user.setFullName("name");
		user.setPassword("you never know");
		user = userDAO.update(user);
		assertEquals("you never know", user.getPassword());
	}
	
	@Test
	public void testGetUsers() {
		int userId = 1; 
		Users user = userDAO.get(userId);
		System.out.println(user.getEmail());
//		assertEquals(user.getFullName(), "name");
		assertNotNull(user);
		
	}
	
	@Test
	public void testGetUserNotFound() { //发现问题了，refresh之前一定要check是不是null
		int userId = 20; 
		Users user = userDAO.get(userId);
//		System.out.println(user.getEmail());
//		assertEquals(user.getFullName(), "name");
		assertNull(user);
		
	}
	
	@Test
	public void testDeleteUsers() {
		int userId = 5;
		userDAO.delete(userId);
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteUserNonExist() {
		int userId = 99;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll()
	{
		List<Users> listUsers = userDAO.listAll();
		for(Users user:listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size()==2);
	}
	
	@Test
	public void testCount() {
		long userNum = userDAO.count();
		assertEquals(userNum, 17);
				
	}
	
	@Test 
	public void testFindByEmails() {
		String email = "lala@codejava.net";
		Users users = userDAO.findByEmail(email);
		assertNotNull(users);
	}

}
