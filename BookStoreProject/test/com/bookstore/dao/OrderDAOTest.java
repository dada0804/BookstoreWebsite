package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;


import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {
	
	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(13);
		
		order.setCustomer(customer);
		order.setRecipientName("Yan");
		order.setRecipientPhone("1111");
		order.setShippingAddress("123 Street");
		order.setPaymentMethod("lala");
		order.setStatus("paid");

		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(34);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		
		BookOrder createdOrder = orderDAO.create(order);
		assertTrue(createdOrder != null && createdOrder.getOrderDetails().size()>0);
		
		
		
		
	}
	
	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(16);
		
		order.setCustomer(customer);
		order.setRecipientName("mayan");
		order.setRecipientPhone("1111");
		order.setShippingAddress("123 Street");
		order.setPaymentMethod("lala");
		order.setStatus("paid");

		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book1 = new Book(45);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(5);
		orderDetail1.setBookOrder(order);
		orderDetails.add(orderDetail1);

		OrderDetail orderDetail2 = new OrderDetail();
		Book book2 = new Book(46);
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(2);
		orderDetail2.setBookOrder(order);	
		orderDetails.add(orderDetail2);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder createdOrder = orderDAO.create(order);
		assertTrue(order.getOrderId()>0 && createdOrder.getOrderDetails().size()==2);
		
		
		
		
	}

	@Test
	public void testUpdateBookAddress() {
		Integer orderId = 29;
		BookOrder order = orderDAO.get(orderId);
		order.setShippingAddress("New");

		BookOrder updatedOrder = orderDAO.update(order);
		assertTrue(order.getShippingAddress().equals("New"));
	}
	
	@Test
	public void testUpdateBookOrderDetail () {
		Integer orderId = 28;
		BookOrder order = orderDAO.get(orderId);
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getBook().getBookId() == 45) {
				orderDetail.setQuantity(10);
			}
		}
		BookOrder updatedOrder = orderDAO.update(order);
		int expectQty = 0; 
		Iterator<OrderDetail> iterator2 = updatedOrder.getOrderDetails().iterator();
		while(iterator2.hasNext()) {
			OrderDetail orderDetail = iterator2.next();
			if(orderDetail.getBook().getBookId() == 45) {
				expectQty = orderDetail.getQuantity();
				System.out.println(orderDetail.getQuantity());

			}
		}
		assertTrue(expectQty == 10);
		
	}

	@Test
	public void testGet() {
		Integer orderId = 28;
		BookOrder order = orderDAO.get(orderId);
		assertTrue(order.getOrderDetails().size() ==2);
	}

	@Test
	public void testDeleteObject() {
		Integer orderId = 31;
		orderDAO.delete(orderId);
		BookOrder bookOrder = orderDAO.get(orderId);
		assertNull(bookOrder);
	}

	@Test
	public void testListAll() {
		List<BookOrder> bookOrders = orderDAO.listAll();
		for (BookOrder order : bookOrders) {
			System.out.println(order.getPaymentMethod());
		}
		assertTrue(bookOrders.size() > 0);
	}

	@Test
	public void testCount() {
		long cnt = orderDAO.count();
		assertTrue(cnt == 2);
	}
	
	@Test
	public void testListCustomer() {
		Integer customerId = 16; 
		List<BookOrder> bookOrders = orderDAO.listByCustomer(customerId);
		for (BookOrder order : bookOrders) {
			System.out.println(order.getTotal());
		}
		assertNotNull(bookOrders);
		
		
	}
	
	@Test
	public void testListCustomerNotExist() {
		Integer customerId = 199; 
		List<BookOrder> bookOrders = orderDAO.listByCustomer(customerId);
		assertTrue(bookOrders.isEmpty());
			
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrder> bookOrders = orderDAO.listMostRecentSales();
		
		assertTrue(bookOrders.size()==3);
		
	}

}
