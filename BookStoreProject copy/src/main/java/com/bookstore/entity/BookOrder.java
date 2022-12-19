package com.bookstore.entity;
// Generated Sep 27, 2022, 1:35:27 PM by Hibernate Tools 5.4.33.Final

import java.util.Date;

/**
 * BookOrder generated by hbm2java
 */
public class BookOrder implements java.io.Serializable {

	private int orderId;
	private Customer customer;
	private Date orderDate;
	private String shippingAddress;
	private String receipientName;
	private String receipientPhone;
	private String paymentMethod;
	private Long orderTotal;
	private String orderStatus;
	private OrderDetail orderDetail;

	public BookOrder() {
	}

	public BookOrder(int orderId, Customer customer, Date orderDate, String shippingAddress, String receipientName,
			String receipientPhone, String paymentMethod) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.receipientName = receipientName;
		this.receipientPhone = receipientPhone;
		this.paymentMethod = paymentMethod;
	}

	public BookOrder(int orderId, Customer customer, Date orderDate, String shippingAddress, String receipientName,
			String receipientPhone, String paymentMethod, Long orderTotal, String orderStatus,
			OrderDetail orderDetail) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.receipientName = receipientName;
		this.receipientPhone = receipientPhone;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.orderDetail = orderDetail;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getReceipientName() {
		return this.receipientName;
	}

	public void setReceipientName(String receipientName) {
		this.receipientName = receipientName;
	}

	public String getReceipientPhone() {
		return this.receipientPhone;
	}

	public void setReceipientPhone(String receipientPhone) {
		this.receipientPhone = receipientPhone;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(Long orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderDetail getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

}
