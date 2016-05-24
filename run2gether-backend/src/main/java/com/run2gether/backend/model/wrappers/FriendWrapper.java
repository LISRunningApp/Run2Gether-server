package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Friend;

@JsonRootName(value = "friend")
public class FriendWrapper {

	final Logger log = Logger.getLogger(UsersWrapper.class);
	private List<Friend> friend;

	public FriendWrapper(List<Friend> friend) {
		this.friend = friend;
	}

	@JsonValue
	public List<Friend> getFriend() {
		return friend;
	}

	public void setFriend(List<Friend> list) {
		friend = list;
	}

}
