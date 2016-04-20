package com.run2gether.backend.model;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity
@Table(name="users")
public class Users implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String name;
	private String email;
	private static final long serialVersionUID = 1L;

	public Users() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
