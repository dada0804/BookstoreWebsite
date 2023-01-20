package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest{
	
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {

		Book book = new Book();
		Category cat = new Category("Literature");
		cat.setCategoryId(17);
		book.setCategory(cat);

		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setDescription("The Definitive Guide to Java Platform Best Practices-Updated for Java 7, 8, and 9");
		book.setIsbn("0134685997");
		book.setPrice(20.25f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/22/2020");
		book.setPublishDate(publishDate);
		
		String imagePath ="/Users/macbook/Desktop/books_img/effective_java.jpeg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book createBook = bookDAO.create(book);
		
		assertTrue(createBook.getBookId()>0);
		
	}
	
	@Test
	public void testCreateSecondBook() throws ParseException, IOException {

		Book book = new Book();
		Category cat = new Category("Psychology");
		cat.setCategoryId(12);
		book.setCategory(cat);

		book.setTitle("Psychology the basics");
		book.setAuthor("Joshua Bloch");
		book.setDescription("This book, which includes further reading in each chapter for those wishing to study more deeply, is the perfect easy-to-understand introductory text f");
		book.setIsbn("013468234997");
		book.setPrice(30.25f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/22/2022");
		book.setPublishDate(publishDate);
		
		String imagePath ="/Users/macbook/Desktop/books_img/psy.png";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book createBook = bookDAO.create(book);
		
		assertTrue(createBook.getBookId()>0);
		
	}
	
	@Test
	public void testUpdateBookSuccess() throws ParseException, IOException {

		Book book = new Book();
		book.setBookId(36);
		Category cat = new Category("what");
		cat.setCategoryId(14);
		book.setCategory(cat);

		book.setTitle("Effective Java third edition-changeeeeeeee");
		book.setAuthor("Joshua Bloch");
		book.setDescription("The Definitive Guide to Java Platform Best Practices-Updated for Java 7, 8, and 9");
		book.setIsbn("0134685997");
		book.setPrice(60.25f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/22/2020");
		book.setPublishDate(publishDate);
		
		String imagePath ="/Users/macbook/Desktop/books_img/effective_java.jpeg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book updateBook = bookDAO.update(book);
		
		assertEquals(book.getCategory(), updateBook.getCategory());
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void  testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);
		assertTrue(true);
	}
	
	@Test 
	public void  testDeleteBookSuccess() {
		Integer bookId = 36;
		bookDAO.delete(bookId);
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDAO.get(bookId);
		assertEquals(book, null);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 33;
		Book book = bookDAO.get(bookId);
		assertEquals(book.getTitle(), "Effective Java");
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		for (Book b : listBooks) {
			System.out.println(b.getTitle());
		}
		assertFalse(listBooks.isEmpty());
	}
	
	@Test 
	public void testFindByTitleExist() {
		Book book = bookDAO.findByTitle("Psychology the basics");
		assertEquals(book.getIsbn(),"013468234997");
	}
	
	@Test 
	public void testFindByTitleNotExisted() {
		Book book = bookDAO.findByTitle("Psychology");
		assertNull(book);
	}
	
	@Test
	public void testCount() {
		long cnt = bookDAO.count();
		assertEquals(cnt, 3);
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 12;
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		assertTrue(listBooks.size()==2);
	}
	
	@Test
	public void testListByCategoryFail() {
		int categoryId = 13;
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		assertTrue(listBooks.size()==0);
	}
	
	@Test
	public void testListNew() {
		List<Book> books = bookDAO.listNewBooks();
		for(Book b: books) {
			System.out.println(b.getTitle()+ " - " + b.getPublishDate());
		}
		assertTrue(books.size()==4);
	}
	
	@Test
	public void testSearch() {
		String keyword = "Java";
		List<Book> listBook = bookDAO.search(keyword);
		for(Book b: listBook) {
			System.out.println(b.getTitle()+ " - " + b.getPublishDate());
		}
		assertTrue(listBook.size()>0);
	}
	
	@Test
	public void testSearchAuthor() {
		String keyword = "Joshua";
		List<Book> listBook = bookDAO.search(keyword);
		for(Book b: listBook) {
			System.out.println(b.getTitle()+ " - " + b.getAuthor());
		}
		assertTrue(listBook.size()==2);
	}
	
	@Test
	public void testSearchDescription() {
		String keyword = "Joshua";
		List<Book> listBook = bookDAO.search(keyword);
		for(Book b: listBook) {
			System.out.println(b.getTitle()+ " - " + b.getDescription());
		}
		assertTrue(listBook.size()==2);
	}
	
	@Test
	public void testSearchAll() {
		String keyword = "Java";
		List<Book> listBook = bookDAO.search(keyword);
		for(Book b: listBook) {
			System.out.println(b.getTitle()+ " - " + b.getDescription());
		}
		assertTrue(listBook.size()==4);
	}



}
