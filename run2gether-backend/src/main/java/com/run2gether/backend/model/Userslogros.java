package com.run2gether.backend.model;
// Generated 07-may-2016 22:23:29 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Userslogros generated by hbm2java
 */
@Entity
@Table(name = "userslogros", catalog = "run2gether_dev", uniqueConstraints = {
		@UniqueConstraint(columnNames = "idLogro"), @UniqueConstraint(columnNames = "idUser") })
public class Userslogros implements java.io.Serializable {

	private static final long serialVersionUID = 7L;
	private UserslogrosId id;
	private Logro logro;
	private User user;
	private String dateLogro;

	public Userslogros() {
	}

	public Userslogros(UserslogrosId id, Logro logro, User user) {
		this.id = id;
		this.logro = logro;
		this.user = user;
	}

	public Userslogros(UserslogrosId id, Logro logro, User user, String dateLogro) {
		this.id = id;
		this.logro = logro;
		this.user = user;
		this.dateLogro = dateLogro;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idLogro", column = @Column(name = "idLogro", unique = true, nullable = false)),
			@AttributeOverride(name = "idUser", column = @Column(name = "idUser", unique = true, nullable = false)) })
	public UserslogrosId getId() {
		return this.id;
	}

	public void setId(UserslogrosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLogro", unique = true, nullable = false, insertable = false, updatable = false)
	public Logro getLogros() {
		return this.logro;
	}

	public void setLogros(Logro logro) {
		this.logro = logro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", unique = true, nullable = false, insertable = false, updatable = false)
	public User getUsers() {
		return this.user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	@Column(name = "dateLogro", length = 45)
	public String getDateLogro() {
		return this.dateLogro;
	}

	public void setDateLogro(String dateLogro) {
		this.dateLogro = dateLogro;
	}

}