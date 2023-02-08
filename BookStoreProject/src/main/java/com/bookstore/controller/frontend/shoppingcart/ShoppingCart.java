package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book, Integer> cart = new HashMap<>();
	
	public void addItem(Book book) {
		if(cart.containsKey(book)) {
			Integer quantity = cart.get(book);
			quantity += 1;
			cart.put(book, quantity);
		}else {
			cart.put(book, 1);
		}
	}	
	
	public void removeItem(Book book) {
		cart.remove(book);
	}
	
	//can't understand why this is needed
	public int getTotalQuantity() {
		int total = 0; 
		Iterator<Book> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book next = iterator.next();
			Integer quantity = cart.get(next);
			total += quantity;
		}
		return total;
		
	}
	
	public void updateCart(int[] bookIds, int[] quantities) {
		for (int i = 0; i < bookIds.length; i ++) {
			Book key = new Book(bookIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		}
	}
	
	public float getTotalAmount() {
		float totalAmount = 0.0f;
		Iterator<Book> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book next = iterator.next();
			float price = next.getPrice();
			Integer quantity = cart.get(next);
			float amount = price * quantity;
			totalAmount += amount;
		}
		return totalAmount;	
	}
	
	public Map<Book, Integer> getItems(){
		return this.cart;
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public void clear() {
		cart.clear();
	}
	
}
