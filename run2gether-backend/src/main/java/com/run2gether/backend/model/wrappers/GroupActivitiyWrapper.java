package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Groupactivity;

@JsonRootName(value = "groupActivitiy")
public class GroupActivitiyWrapper {

	final Logger log = Logger.getLogger(UsersWrapper.class);
	private List<Groupactivity> groupActivity;

	public GroupActivitiyWrapper(List<Groupactivity> groupActivitiy) {
		groupActivity = groupActivitiy;
	}

	@JsonValue
	public List<Groupactivity> getGroupActivitiy() {
		return groupActivity;
	}

	public void setGroupActivitiy(List<Groupactivity> list) {
		groupActivity = list;
	}

}
