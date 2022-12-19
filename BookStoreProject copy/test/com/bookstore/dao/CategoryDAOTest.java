package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

import net.bytebuddy.implementation.bind.annotation.Super;

public class CategoryDAOTest extends BaseDAOTest {
	
	private static CategoryDAO categoryDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager); 
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreate() {
		String name = "business";
		Category category = new Category(name);
		categoryDAO.create(category);
		assertTrue(category.getCategoryId()>0);
		
	}
	
	@Test(expected = PersistenceException.class)
	// name cannot be null  
	public void testCreateFailed() {
		Category category = new Category();
		categoryDAO.create(category);
		assertTrue(category.getCategoryId()>0);
	}
	
	@Test
	public void testGet() {
		Category category = categoryDAO.get(11);
		assertEquals(category.getName(), "business");
	}
	
	@Test
	public void testGetNotFound() {
		Category category = categoryDAO.get(1);
		assertNull(category);;
	}
	
	@Test
	public void testUpdate() {
		Category category = new Category(11,"education");
		categoryDAO.update(category);
		assertNotEquals(category.getName(), "business");
	}
	
	@Test
	public void testDelete() {
		categoryDAO.delete(11);
		assertNull(categoryDAO.get(11));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExist() {
		categoryDAO.delete(14);
		
	}
	
	@Test
	public void testListAll() {
		categoryDAO.create(new Category("Psychology"));
		categoryDAO.create(new Category("Mathematics"));
		categoryDAO.create(new Category("Literature"));
		List<Category>  categories = categoryDAO.listAll();
		categories.forEach(c->System.out.println(c.getName() + " "));
		assertEquals(categories.size(), 3);	
	}
	
	@Test
	public void count() {
		assertEquals(categoryDAO.count(),3);
	}
	
	@Test
	public void testFindByName() {
		String name = "dd";
		
		assertNull(categoryDAO.findByname(name));
	}
	
	@Test
	public void testFindByNameNotExisted() {
		String name = "we";
		
		assertNull(categoryDAO.findByname(name));
	}
	
	
	
	

}
