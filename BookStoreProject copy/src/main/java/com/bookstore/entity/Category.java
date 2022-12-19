package com.bookstore.entity;
// Generated Sep 27, 2022, 1:35:27 PM by Hibernate Tools 5.4.33.Final

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Category generated by hbm2java
 */
@NamedQueries({
	@NamedQuery(name = "category.findAll", query = "SELECT c FROM Category c ORDER BY c.name"),
	@NamedQuery(name = "category.countAll", query = "SELECT COUNT(c) FROM Category c"),
	@NamedQuery(name = "category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
})

@Entity
@Table
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String name;
//	private Set<Book> books = new HashSet<Book>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}
	
	

//	public Category(String name, Set<Book> books) {
//		this.name = name;
//		this.books = books;
//	}

	public Category(Integer categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	@Column(name = "category_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
//
//	public Set<Book> getBooks() {
//		return this.books;
//	}
//
//	public void setBooks(Set<Book> books) {
//		this.books = books;
//	}

}