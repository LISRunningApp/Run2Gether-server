package com.run2gether.backend.model;
// Generated 16-may-2016 18:34:41 by Hibernate Tools 5.1.0.Alpha1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "run2gether_dev", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username") })
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 8L;
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	private Date creationDate;
	private Date lastLogin;
	private String status;
	private String loginType;
	private Integer age;
	private Integer size;
	private Float weight;
	private String sex;
	private Set<Userslogros> userslogroses = new HashSet<Userslogros>(0);
	private Set<Groupactivities> groupactivitieses = new HashSet<Groupactivities>(0);
	private Set<Usersgroupactivities> usersgroupactivitieses = new HashSet<Usersgroupactivities>(0);
	private Set<Activities> activitieses = new HashSet<Activities>(0);

	public Users() {
	}

	public Users(String name, String email, String username, String password, Date creationDate, String loginType) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.creationDate = creationDate;
		this.loginType = loginType;
	}

	public Users(String name, String surname, String email, String username, String password, Date creationDate,
			Date lastLogin, String status, String loginType, Integer age, Integer size, Float weight, String sex,
			Set<Userslogros> userslogroses, Set<Groupactivities> groupactivitieses,
			Set<Usersgroupactivities> usersgroupactivitieses, Set<Activities> activitieses) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.creationDate = creationDate;
		this.lastLogin = lastLogin;
		this.status = status;
		this.loginType = loginType;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.sex = sex;
		this.userslogroses = userslogroses;
		this.groupactivitieses = groupactivitieses;
		this.usersgroupactivitieses = usersgroupactivitieses;
		this.activitieses = activitieses;
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

	@Column(name = "name", nullable = false, length = 80)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname", length = 160)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, length = 19)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", length = 19)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "status", length = 12)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "login_type", nullable = false, length = 3)
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "size")
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Column(name = "weight", precision = 12, scale = 0)
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Userslogros> getUserslogroses() {
		return userslogroses;
	}

	public void setUserslogroses(Set<Userslogros> userslogroses) {
		this.userslogroses = userslogroses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Groupactivities> getGroupactivitieses() {
		return groupactivitieses;
	}

	public void setGroupactivitieses(Set<Groupactivities> groupactivitieses) {
		this.groupactivitieses = groupactivitieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Usersgroupactivities> getUsersgroupactivitieses() {
		return usersgroupactivitieses;
	}

	public void setUsersgroupactivitieses(Set<Usersgroupactivities> usersgroupactivitieses) {
		this.usersgroupactivitieses = usersgroupactivitieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Activities> getActivitieses() {
		return activitieses;
	}

	public void setActivitieses(Set<Activities> activitieses) {
		this.activitieses = activitieses;
	}

}