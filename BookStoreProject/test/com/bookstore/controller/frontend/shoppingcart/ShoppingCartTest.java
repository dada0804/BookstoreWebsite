package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		Book book = new Book(1);	
		book.setPrice(20.0f);
		cart.addItem(book);
		cart.addItem(book);
		
	}



	@Test
	public void testAddNewItem() {		
		Map<Book, Integer> items = cart.getItems();
		int quantity = items.get(new Book(1));
		System.out.println(quantity);
		assertTrue(quantity ==2);
	}

	
	
	@Test
	public void testRemoveItem() {
		Book book = new Book(2);
		cart.addItem(book);
		cart.removeItem(book);	
		Map<Book, Integer> items = cart.getItems();
		assertNull(items.get(book));
	}
	
	@Test
	public void testTotalQuantity() {
		Book book = new Book(2);
		cart.addItem(book);
		cart.addItem(book);
		int quantity = cart.getTotalQuantity();
		assertTrue(quantity == 4);
	}
	
	@Test
	public void testTotalAmount() {
		assertTrue(cart.getTotalAmount()==40.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(new Book(48));
		cart.addItem(new Book(34));
		cart.addItem(new Book(42));

		int[] bookIds = {48,34,42};
		int[] quantities = {3,4,2};
		cart.updateCart(bookIds, quantities);
		System.out.println(cart.getTotalQuantity());
		assertTrue(cart.getTotalQuantity()==9);
		
		
		
				
	}
	
	
	

}
