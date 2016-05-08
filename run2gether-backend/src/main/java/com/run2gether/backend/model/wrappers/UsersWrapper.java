package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Users;

@JsonRootName(value = "users")
public class UsersWrapper {

	final Logger log = Logger.getLogger(UsersWrapper.class);

	public UsersWrapper(List<Users> users) {
		this.users = users;
	}

	private List<Users> users;

	@JsonValue
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> list) {
		users = list;
	}

}
