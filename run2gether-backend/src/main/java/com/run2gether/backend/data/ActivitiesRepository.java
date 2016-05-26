
package com.run2gether.backend.data;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.QActivity;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Repository
public class ActivitiesRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(ActivitiesRepository.class);

	@Transactional
	public ActivitiesWrapper getActivities(Date date, String username) {
		JPQLQuery<Activity> query = new JPAQuery<>(em);
		QActivity qu = QActivity.activity;
		BooleanExpression wh = QActivity.activity.user.username.eq(username)
				.and(QActivity.activity.dateModified.eq(date));
		ActivitiesWrapper activityWrapper = new ActivitiesWrapper(query.from(qu).where(wh).fetch());
		return activityWrapper;
	}

	@Transactional
	public void postActivity(Activity newActivities) {
		newActivities.setDateModified(new LocalDateTime().toDate());
		em.persist(newActivities);
	}
}
