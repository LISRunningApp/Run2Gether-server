package com.run2gether.backend.model;
// Generated 10-may-2016 17:28:44 by Hibernate Tools 5.1.0.Alpha1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Activities generated by hbm2java
 */
@Entity
@Table(name = "activities", catalog = "run2gether_dev")
public class Activities implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Users users;
	private String route;
	private String timeKm;
	private Double km;
	private String duration;

	public Activities() {
	}

	public Activities(Users users) {
		this.users = users;
	}

	public Activities(Users users, String route, String timeKm, Double km, String duration) {
		this.users = users;
		this.route = route;
		this.timeKm = timeKm;
		this.km = km;
		this.duration = duration;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "route")
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Column(name = "timeKM")
	public String getTimeKm() {
		return timeKm;
	}

	public void setTimeKm(String timeKm) {
		this.timeKm = timeKm;
	}

	@Column(name = "km", precision = 22, scale = 0)
	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	@Column(name = "duration", length = 45)
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
