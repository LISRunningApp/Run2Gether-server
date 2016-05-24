package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Usergroupactivity;

@JsonRootName(value = "userGroupActivity")
public class UserGroupActivityWrapper {

	final Logger log = Logger.getLogger(Usergroupactivity.class);
	private List<Usergroupactivity> userGroupActivity;

	public UserGroupActivityWrapper(List<Usergroupactivity> userGroupActivity) {
		this.userGroupActivity = userGroupActivity;
	}

	@JsonValue
	public List<Usergroupactivity> getUserGroupActivity() {
		return userGroupActivity;
	}

	public void setUserGroupActivity(List<Usergroupactivity> list) {
		userGroupActivity = list;
	}

}
