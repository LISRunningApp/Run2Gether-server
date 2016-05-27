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
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Repository
public class ActivityRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(ActivityRepository.class);

	/*
	 * @Transactional public ActivitiesWrapper setActivities(Date date, String
	 * username) { JPQLQuery<Activity> query = new JPAQuery<>(em); QActivity qu
	 * = QActivity.activity; BooleanExpression wh =
	 * QActivity.activity.user.username.eq(username)
	 * .and(QActivity.activity.dateModified.eq(date)); ActivitiesWrapper
	 * activityWrapper = new
	 * ActivitiesWrapper(query.from(qu).where(wh).fetch()); return
	 * activityWrapper; }
	 */
	@Transactional
	public Integer post(Activity newActivities, User user) {
		newActivities.setDateModified(new LocalDateTime().toDate());
		newActivities.setUser(user);
		em.persist(newActivities);
		em.flush();
		return newActivities.getId();
	}

	@Transactional
	public ActivitiesWrapper get(Integer idActivity, User user) {
		JPQLQuery<Activity> query = new JPAQuery<>(em);
		QActivity qa = QActivity.activity;
		BooleanExpression wh = QActivity.activity.id.eq(idActivity).and(QActivity.activity.user.eq(user));
		ActivitiesWrapper activityWrapper = new ActivitiesWrapper(query.from(qa).where(wh).fetch());
		return activityWrapper;
	}

	@Transactional
	public ActivitiesWrapper get(User user, Date date) {
		JPQLQuery<Activity> query = new JPAQuery<>(em);
		QActivity qa = QActivity.activity;
		BooleanExpression wh = QActivity.activity.user.eq(user).and(QActivity.activity.dateModified.gt(date));
		ActivitiesWrapper activityWrapper = new ActivitiesWrapper(query.from(qa).where(wh).fetch());
		return activityWrapper;
	}

	@Transactional
	public void put(Activity activity) {
		activity.setDateModified(new LocalDateTime().toDate());
		em.merge(activity);
		em.flush();
	}
}
