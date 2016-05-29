package com.run2gether.backend.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Controller
public class GroupActivityController {
	private Logger log = Logger.getLogger(GroupActivityController.class);

	public Integer NumeroGroupActivity(ActivitiesWrapper listActivity) {
		Integer value = 0;
		for (Activity activity : listActivity.getActivities())
			if (!activity.getIdGroupactivities().equals(null))
				value = +1;
		return value;
	}

}
