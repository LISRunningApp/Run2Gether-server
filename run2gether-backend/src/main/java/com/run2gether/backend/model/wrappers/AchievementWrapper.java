package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Achievement;

@JsonRootName(value = "achievement")
public class AchievementWrapper {

	final Logger log = Logger.getLogger(AchievementWrapper.class);
	private List<Achievement> achievement;

	public AchievementWrapper(List<Achievement> achievement) {
		this.achievement = achievement;
	}

	@JsonValue
	public List<Achievement> getAchievement() {
		return achievement;
	}

	public void setAchievement(List<Achievement> list) {
		achievement = list;
	}

}
