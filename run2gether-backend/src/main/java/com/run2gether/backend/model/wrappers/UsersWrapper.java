package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.User;

@JsonRootName(value = "users")
public class UsersWrapper {

	final Logger log = Logger.getLogger(UsersWrapper.class);
	private List<User> user;

	public UsersWrapper(List<User> user) {
		this.user = user;
	}

	@JsonValue
	public List<User> getUser() {
		return user;
	}

	public void setUsers(List<User> list) {
		user = list;
	}

}
