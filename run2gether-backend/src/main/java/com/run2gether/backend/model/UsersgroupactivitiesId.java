package com.run2gether.backend.model;
// Generated 16-may-2016 18:34:41 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsersgroupactivitiesId generated by hbm2java
 */
@Embeddable
public class UsersgroupactivitiesId implements java.io.Serializable {

	private static final long serialVersionUID = 10L;
	private int idGroupActivities;
	private int idUser;

	public UsersgroupactivitiesId() {
	}

	public UsersgroupactivitiesId(int idGroupActivities, int idUser) {
		this.idGroupActivities = idGroupActivities;
		this.idUser = idUser;
	}

	@Column(name = "idGroupActivities", nullable = false)
	public int getIdGroupActivities() {
		return idGroupActivities;
	}

	public void setIdGroupActivities(int idGroupActivities) {
		this.idGroupActivities = idGroupActivities;
	}

	@Column(name = "idUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof UsersgroupactivitiesId))
			return false;
		UsersgroupactivitiesId castOther = (UsersgroupactivitiesId) other;

		return getIdGroupActivities() == castOther.getIdGroupActivities() && getIdUser() == castOther.getIdUser();
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + getIdGroupActivities();
		result = 37 * result + getIdUser();
		return result;
	}

}
