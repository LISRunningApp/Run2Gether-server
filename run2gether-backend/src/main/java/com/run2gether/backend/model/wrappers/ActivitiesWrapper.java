package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Activity;

@JsonRootName(value = "activities")
public class ActivitiesWrapper {

	final Logger log = Logger.getLogger(ActivitiesWrapper.class);
	private List<Activity> activity;

	public ActivitiesWrapper(List<Activity> activities) {
		activity = activities;
	}

	@JsonValue
	public List<Activity> getActivities() {
		return activity;
	}

	public void setUsers(List<Activity> list) {
		activity = list;
	}
}
