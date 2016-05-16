package com.run2gether.backend.model;
// Generated 16-may-2016 18:34:41 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Userslogros generated by hbm2java
 */
@Entity
@Table(name = "userslogros", catalog = "run2gether_dev", uniqueConstraints = {
		@UniqueConstraint(columnNames = "idLogro"), @UniqueConstraint(columnNames = "idUser") })
public class Userslogros implements java.io.Serializable {

	private static final long serialVersionUID = 11L;
	private UserslogrosId id;
	private Logros logros;
	private Users users;
	private Date dateLogro;

	public Userslogros() {
	}

	public Userslogros(UserslogrosId id, Logros logros, Users users) {
		this.id = id;
		this.logros = logros;
		this.users = users;
	}

	public Userslogros(UserslogrosId id, Logros logros, Users users, Date dateLogro) {
		this.id = id;
		this.logros = logros;
		this.users = users;
		this.dateLogro = dateLogro;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idLogro", column = @Column(name = "idLogro", unique = true, nullable = false)),
			@AttributeOverride(name = "idUser", column = @Column(name = "idUser", unique = true, nullable = false)) })
	public UserslogrosId getId() {
		return id;
	}

	public void setId(UserslogrosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLogro", unique = true, nullable = false, insertable = false, updatable = false)
	public Logros getLogros() {
		return logros;
	}

	public void setLogros(Logros logros) {
		this.logros = logros;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", unique = true, nullable = false, insertable = false, updatable = false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateLogro", length = 19)
	public Date getDateLogro() {
		return dateLogro;
	}

	public void setDateLogro(Date dateLogro) {
		this.dateLogro = dateLogro;
	}

}
