package com.run2gether.backend.model;
// Generated 07-may-2016 22:23:29 by Hibernate Tools 5.1.0.Alpha1

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

/**
 * Groupactivity generated by hbm2java
 */
@Entity
@Table(name = "groupactivities", catalog = "run2gether_dev")
public class Groupactivity implements java.io.Serializable {

	private static final long serialVersionUID = 2L;
	private int idGroupActivities;
	private User user;
	private String startDate;
	private String resume;
	private Set<Usersgroupactivities> usersgroupactivitieses = new HashSet<Usersgroupactivities>(0);

	public Groupactivity() {
	}

	public Groupactivity(int idGroupActivities, User user) {
		this.idGroupActivities = idGroupActivities;
		this.user = user;
	}

	public Groupactivity(int idGroupActivities, User user, String startDate, String resume,
			Set<Usersgroupactivities> usersgroupactivitieses) {
		this.idGroupActivities = idGroupActivities;
		this.user = user;
		this.startDate = startDate;
		this.resume = resume;
		this.usersgroupactivitieses = usersgroupactivitieses;
	}

	@Id

	@Column(name = "idGroupActivities", unique = true, nullable = false)
	public int getIdGroupActivities() {
		return this.idGroupActivities;
	}

	public void setIdGroupActivities(int idGroupActivities) {
		this.idGroupActivities = idGroupActivities;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAdmin", nullable = false)
	public User getUsers() {
		return this.user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	@Column(name = "StartDate", length = 45)
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "Resume")
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupactivities")
	public Set<Usersgroupactivities> getUsersgroupactivitieses() {
		return this.usersgroupactivitieses;
	}

	public void setUsersgroupactivitieses(Set<Usersgroupactivities> usersgroupactivitieses) {
		this.usersgroupactivitieses = usersgroupactivitieses;
	}

}