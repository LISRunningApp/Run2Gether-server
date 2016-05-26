package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.run2gether.backend.model.Groupactivity;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.Usergroupactivity;

@Repository
public class UserGroupActivityRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(UserGroupActivityRepository.class);

	@Transactional
	public void post(Usergroupactivity newUserGroupActivity, User user, Groupactivity groupactivity) {
		newUserGroupActivity.setDate(new LocalDateTime().toDate());
		newUserGroupActivity.setDateModified(new LocalDateTime().toDate());
		newUserGroupActivity.setUser(user);
		newUserGroupActivity.setGroupactivity(groupactivity);
		em.persist(newUserGroupActivity);
	}
}
