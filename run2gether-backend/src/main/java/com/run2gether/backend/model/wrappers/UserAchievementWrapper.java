package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Userachievement;

@JsonRootName(value = "userAchievement")
public class UserAchievementWrapper {

	final Logger log = Logger.getLogger(Userachievement.class);
	private List<Userachievement> userAchievement;

	public UserAchievementWrapper(List<Userachievement> userAchievement) {
		this.userAchievement = userAchievement;
	}

	@JsonValue
	public List<Userachievement> getUserAchievement() {
		return userAchievement;
	}

	public void setUserAchievement(List<Userachievement> list) {
		userAchievement = list;
	}

}
