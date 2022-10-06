package com.bookstore.entity;
// Generated Sep 27, 2022, 1:35:27 PM by Hibernate Tools 5.4.33.Final

/**
 * OrderDetail generated by hbm2java
 */
public class OrderDetail implements java.io.Serializable {

	private int orderId;
	private Book book;
	private BookOrder bookOrder;
	private int quantity;
	private long subtotal;

	public OrderDetail() {
	}

	public OrderDetail(Book book, BookOrder bookOrder, int quantity, long subtotal) {
		this.book = book;
		this.bookOrder = bookOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
	}

}
