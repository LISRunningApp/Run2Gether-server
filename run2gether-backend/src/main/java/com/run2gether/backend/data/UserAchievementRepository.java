package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.run2gether.backend.model.Achievement;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.Userachievement;

@Repository
public class UserAchievementRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(UserAchievementRepository.class);

	@Transactional
	public void post(Userachievement newUserAchievement, User user, Achievement achievement) {
		newUserAchievement.setDateModified(new LocalDateTime().toDate());
		newUserAchievement.setAchievement(achievement);
		newUserAchievement.setUser(user);
		em.persist(newUserAchievement);
		em.flush();
	}
}
