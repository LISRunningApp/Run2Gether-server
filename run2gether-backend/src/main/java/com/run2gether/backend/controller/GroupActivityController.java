package com.run2gether.backend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.run2gether.backend.data.GroupActivityRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Controller
@Component
public class GroupActivityController {
	private Logger log = Logger.getLogger(GroupActivityController.class);

	@Autowired
	private GroupActivityRepository groupActivityRepository;

	public Integer numeroGroupActivity(ActivitiesWrapper listActivity) {
		Integer value = 0;
		for (Activity activity : listActivity.getActivities())
			if (!activity.getIdGroupactivities().equals(null))
				value = +1;
		return value;
	}

}
