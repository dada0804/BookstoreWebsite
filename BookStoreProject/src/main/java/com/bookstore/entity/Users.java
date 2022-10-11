package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


// when this is used, the ORM will inspect and analyze the class's info for mapping. 
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.fullName"),
	@NamedQuery(name = "Users.countAll", query = "SELECT COUNT (u) FROM Users u"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
})
@Entity 
@Table
//if the name is different, need to add (name = ""). This is optional. 
public class Users {

	private Long userId;
	private String email;
	private String fullName;
	private String password;
	
	public Users() {
		super();
	}

	public Users(String email, String fullName, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}
	


	public Users(Long userId, String email, String fullName, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// id indicate it's primary key.
	// type.identity means generate values at the table level; AUTO means at the
	// database level.

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
