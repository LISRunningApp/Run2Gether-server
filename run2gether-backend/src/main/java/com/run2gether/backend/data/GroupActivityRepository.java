package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.Groupactivity;
import com.run2gether.backend.model.QGroupactivity;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.GroupActivityWrapper;

@Repository
public class GroupActivityRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(GroupActivityRepository.class);

	@Transactional
	public Integer post(Groupactivity newGroupActivitiy, User user) {
		newGroupActivitiy.setDateModified(new LocalDateTime().toDate());
		newGroupActivitiy.setUser(user);
		em.persist(newGroupActivitiy);
		em.flush();
		return newGroupActivitiy.getId();
	}

	@Transactional
	public GroupActivityWrapper get(Integer groupActivity) {
		JPQLQuery<Groupactivity> query = new JPAQuery<>(em);
		QGroupactivity qga = QGroupactivity.groupactivity;
		BooleanExpression wh = QGroupactivity.groupactivity.id.eq(groupActivity);
		GroupActivityWrapper grupActivityWrapper = new GroupActivityWrapper(query.from(qga).where(wh).fetch());
		return grupActivityWrapper;

	}

	@Transactional
	public void put(Groupactivity groupActivity) {
		groupActivity.setDateModified(new LocalDateTime().toDate());
		em.merge(groupActivity);
		em.flush();
	}

}