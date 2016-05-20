package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Activities;

@JsonRootName(value = "activities")
public class ActivitiesWrapper {

	final Logger log = Logger.getLogger(ActivitiesWrapper.class);
	private List<Activities> activities;

	public ActivitiesWrapper(List<Activities> activities) {
		this.activities = activities;
	}

	@JsonValue
	public List<Activities> getActivities() {
		return activities;
	}

	public void setUsers(List<Activities> list) {
		activities = list;
	}
}
