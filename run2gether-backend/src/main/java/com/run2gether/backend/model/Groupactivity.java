package com.run2gether.backend.model;
// Generated 24-may-2016 23:17:21 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Groupactivity generated by hbm2java
 */
@Entity
@Table(name = "groupactivity", catalog = "run2gether_dev")
public class Groupactivity implements java.io.Serializable {

	private static final long serialVersionUID = 7L;
	private int id;
	private User user;
	private Date startDate;
	private String resume;
	private Date dateModified;
	private Set<Usergroupactivity> usergroupactivities = new HashSet<Usergroupactivity>(0);

	public Groupactivity() {
	}

	public Groupactivity(int id, User user, Date startDate) {
		this.id = id;
		this.user = user;
		this.startDate = startDate;
	}

	public Groupactivity(int id, User user, Date startDate, String resume, Date dateModified,
			Set<Usergroupactivity> usergroupactivities) {
		this.id = id;
		this.user = user;
		this.startDate = startDate;
		this.resume = resume;
		this.dateModified = dateModified;
		this.usergroupactivities = usergroupactivities;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_admin", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false, length = 19)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "resume")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 19)
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupactivity")
	public Set<Usergroupactivity> getUsergroupactivities() {
		return usergroupactivities;
	}

	public void setUsergroupactivities(Set<Usergroupactivity> usergroupactivities) {
		this.usergroupactivities = usergroupactivities;
	}

	public void update(Groupactivity modyAtt) {
		startDate = modyAtt.startDate == null ? startDate : modyAtt.startDate;
		resume = modyAtt.resume == null ? resume : modyAtt.resume;

	}

}
