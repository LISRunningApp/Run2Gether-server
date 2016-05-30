package com.run2gether.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.JsonObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.run2gether.backend.data.ActivityRepository;
import com.run2gether.backend.data.GroupActivityRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Groupactivity;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Controller
public class GroupActivityController {
	private Logger log = Logger.getLogger(GroupActivityController.class);

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private GroupActivityRepository groupActivityRepository;

	public Integer numeroGroupActivity(ActivitiesWrapper listActivity) {
		Integer value = 0;
		for (Activity activity : listActivity.getActivities())
			if (!activity.getIdGroupactivities().equals(null))
				value = +1;
		return value;
	}

	public Integer creationGrouActivityAndActivity(String userName, JsonObject json) {
		Boolean result = false;
		Integer createGroupActivity = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		User user = userRepository.getUserByUniquekey(userName);
		Groupactivity gactivity = new Groupactivity();
		gactivity.setUser(userRepository.getUserByUniquekey(userName));
		gactivity.setResume(json.getString("descripcion"));
		try {
			gactivity.setStartDate(formato.parse(json.getString("fecha").replaceAll("\"", "")));
			Activity newActivities;
			createGroupActivity = groupActivityRepository.post(gactivity, user);
			for (String userList : json.getString("listaUsuarios").split("/")) {
				newActivities = new Activity();
				newActivities.setIdGroupactivities(createGroupActivity);
				activityRepository.post(newActivities, userRepository.getUserByUniquekey(userList));
			}
			newActivities = new Activity();
			newActivities.setIdGroupactivities(createGroupActivity);
			activityRepository.post(newActivities, user);

			result = true;
		} catch (ParseException e) {
			result = false;
		}

		return createGroupActivity;
	}

}
