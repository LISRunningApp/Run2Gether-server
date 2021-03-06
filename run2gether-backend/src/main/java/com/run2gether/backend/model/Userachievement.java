package com.run2gether.backend.model;
// Generated 24-may-2016 23:17:21 by Hibernate Tools 5.1.0.Alpha1

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
 * Userachievement generated by hbm2java
 */
@Entity
@Table(name = "userachievement", catalog = "run2gether_dev")
public class Userachievement implements java.io.Serializable {

	private static final long serialVersionUID = 9L;
	private UserachievementId id;
	private Achievement achievement;
	private User user;
	private Date dateAchievement;
	private Date dateModified;

	public Userachievement() {
	}

	public Userachievement(UserachievementId id, Achievement achievement, User user) {
		this.id = id;
		this.achievement = achievement;
		this.user = user;
	}

	public Userachievement(UserachievementId id, Achievement achievement, User user, Date dateAchievement,
			Date dateModified) {
		this.id = id;
		this.achievement = achievement;
		this.user = user;
		this.dateAchievement = dateAchievement;
		this.dateModified = dateModified;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idAchievement", column = @Column(name = "id_achievement", nullable = false)),
			@AttributeOverride(name = "idUser", column = @Column(name = "id_user", nullable = false)) })
	public UserachievementId getId() {
		return id;
	}

	public void setId(UserachievementId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_achievement", nullable = false, insertable = false, updatable = false)
	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_achievement", length = 19)
	public Date getDateAchievement() {
		return dateAchievement;
	}

	public void setDateAchievement(Date dateAchievement) {
		this.dateAchievement = dateAchievement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modified", length = 19)
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

}
