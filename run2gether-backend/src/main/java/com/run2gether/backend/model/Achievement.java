package com.run2gether.backend.model;
// Generated 21-may-2016 10:44:53 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Achievement generated by hbm2java
 */
@Entity
@Table(name = "achievement", catalog = "run2gether_dev", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Achievement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String iconPath;
	private Date dateModified;
	private Set<Userachievement> userachievements = new HashSet<Userachievement>(0);

	public Achievement() {
	}

	public Achievement(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Achievement(int id, String name, String description, String iconPath, Date dateModified,
			Set<Userachievement> userachievements) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.iconPath = iconPath;
		this.dateModified = dateModified;
		this.userachievements = userachievements;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "icon_path")
	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 19)
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "achievement")
	public Set<Userachievement> getUserachievements() {
		return userachievements;
	}

	public void setUserachievements(Set<Userachievement> userachievements) {
		this.userachievements = userachievements;
	}

}
