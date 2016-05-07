package com.run2gether.backend.model.wrappers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.User;

@JsonRootName(value = "users")
public class Users {
	
	public Users(List<User> users) {
		this.users = users;
	}
	
	private List<User> users;
	
	@JsonValue
	public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> list) {
        this.users = list;
    }
	
}
