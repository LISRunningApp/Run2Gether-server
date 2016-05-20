package com.run2gether.backend.data;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.Activities;
import com.run2gether.backend.model.QActivities;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Repository
public class ActivitiesRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(ActivitiesRepository.class);

	@Transactional
	public ActivitiesWrapper setActivities(Date date, String username) {
		JPQLQuery<Activities> query = new JPAQuery<>(em);
		QActivities qu = QActivities.activities;
		BooleanExpression wh = QActivities.activities.users.username.eq(username).and(QActivities.activities.eq(date));
		ActivitiesWrapper activityWrapper = new ActivitiesWrapper(query.from(qu).where(wh).fetch());
		return activityWrapper;
	}
}
