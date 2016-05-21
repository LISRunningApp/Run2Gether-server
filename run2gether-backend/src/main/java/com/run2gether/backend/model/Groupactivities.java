package com.run2gether.backend.model;
// Generated 16-may-2016 18:34:41 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Groupactivities generated by hbm2java
 */
@Entity
@Table(name = "groupactivities", catalog = "run2gether_dev")
public class Groupactivities implements java.io.Serializable {

	private static final long serialVersionUID = 6L;
	private int idGroupActivities;
	private Users users;
	private Date startDate;
	private String resume;
	private Set<Usersgroupactivities> usersgroupactivitieses = new HashSet<Usersgroupactivities>(0);

	public Groupactivities() {
	}

	public Groupactivities(int idGroupActivities, Users users, Date startDate) {
		this.idGroupActivities = idGroupActivities;
		this.users = users;
		this.startDate = startDate;
	}

	public Groupactivities(int idGroupActivities, Users users, Date startDate, String resume,
			Set<Usersgroupactivities> usersgroupactivitieses) {
		this.idGroupActivities = idGroupActivities;
		this.users = users;
		this.startDate = startDate;
		this.resume = resume;
		this.usersgroupactivitieses = usersgroupactivitieses;
	}

	@Id

	@Column(name = "idGroupActivities", unique = true, nullable = false)
	public int getIdGroupActivities() {
		return idGroupActivities;
	}

	public void setIdGroupActivities(int idGroupActivities) {
		this.idGroupActivities = idGroupActivities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAdmin", nullable = false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", nullable = false, length = 19)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "Resume")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupactivities")
	public Set<Usersgroupactivities> getUsersgroupactivitieses() {
		return usersgroupactivitieses;
	}

	public void setUsersgroupactivitieses(Set<Usersgroupactivities> usersgroupactivitieses) {
		this.usersgroupactivitieses = usersgroupactivitieses;
	}

}
