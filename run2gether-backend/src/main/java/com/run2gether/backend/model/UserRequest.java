package com.run2gether.backend.model;

public class UserRequest {

	private String username;
	private String userSubname;
	private String typeLogin;

	public UserRequest(User user) {
		username = user.getUsername();
		userSubname = user.getName() + " " + user.getSurname();
		typeLogin = user.getLoginType();
	}

	public String getTypeLogin() {
		return typeLogin;
	}

	public void setTypeLogin(String typeLogin) {
		this.typeLogin = typeLogin;
	}

	public String getUserSubname() {
		return userSubname;
	}

	public void setUserSubname(String userSubname) {
		this.userSubname = userSubname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
