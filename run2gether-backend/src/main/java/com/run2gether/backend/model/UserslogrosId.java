package com.run2gether.backend.model;
// Generated 10-may-2016 17:28:44 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserslogrosId generated by hbm2java
 */
@Embeddable
public class UserslogrosId implements java.io.Serializable {

	private static final long serialVersionUID = 8L;
	private int idLogro;
	private int idUser;

	public UserslogrosId() {
	}

	public UserslogrosId(int idLogro, int idUser) {
		this.idLogro = idLogro;
		this.idUser = idUser;
	}

	@Column(name = "idLogro", unique = true, nullable = false)
	public int getIdLogro() {
		return idLogro;
	}

	public void setIdLogro(int idLogro) {
		this.idLogro = idLogro;
	}

	@Column(name = "idUser", unique = true, nullable = false)
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
		if (!(other instanceof UserslogrosId))
			return false;
		UserslogrosId castOther = (UserslogrosId) other;

		return getIdLogro() == castOther.getIdLogro() && getIdUser() == castOther.getIdUser();
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + getIdLogro();
		result = 37 * result + getIdUser();
		return result;
	}

}
