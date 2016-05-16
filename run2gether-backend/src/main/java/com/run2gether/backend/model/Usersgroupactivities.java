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

/**
 * Usersgroupactivities generated by hbm2java
 */
@Entity
@Table(name = "usersgroupactivities", catalog = "run2gether_dev")
public class Usersgroupactivities implements java.io.Serializable {

	private static final long serialVersionUID = 9L;
	private UsersgroupactivitiesId id;
	private Groupactivities groupactivities;
	private Users users;
	private Date date;

	public Usersgroupactivities() {
	}

	public Usersgroupactivities(UsersgroupactivitiesId id, Groupactivities groupactivities, Users users, Date date) {
		this.id = id;
		this.groupactivities = groupactivities;
		this.users = users;
		this.date = date;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idGroupActivities", column = @Column(name = "idGroupActivities", nullable = false)),
			@AttributeOverride(name = "idUser", column = @Column(name = "idUser", nullable = false)) })
	public UsersgroupactivitiesId getId() {
		return id;
	}

	public void setId(UsersgroupactivitiesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGroupActivities", nullable = false, insertable = false, updatable = false)
	public Groupactivities getGroupactivities() {
		return groupactivities;
	}

	public void setGroupactivities(Groupactivities groupactivities) {
		this.groupactivities = groupactivities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false, insertable = false, updatable = false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
