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
import com.run2gether.backend.model.Checkpoint;
import com.run2gether.backend.model.QCheckpoint;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Repository
public class CheckpointRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(CheckpointRepository.class);

	@Transactional

	public ActivitiesWrapper setActivities(Date date, String username) {
		JPQLQuery<Activity> query = new JPAQuery<>(em);
		QCheckpoint qc = QCheckpoint.checkpoint;
		QUser qu = QUser.user;
		BooleanExpression wh = QCheckpoint.checkpoint.activity.user.username.eq(username)
				.and(QCheckpoint.checkpoint.dateModified.eq(date));
		ActivitiesWrapper activityWrapper = new ActivitiesWrapper(query.from(qu, qc).where(wh).fetch());
		return activityWrapper;
	}

	@Transactional
	public void post(Checkpoint newCheckpoint, Activity activity) {
		newCheckpoint.setActivity(activity);
		newCheckpoint.setDateModified(new LocalDateTime().toDate());
		em.persist(newCheckpoint);
	}

}
