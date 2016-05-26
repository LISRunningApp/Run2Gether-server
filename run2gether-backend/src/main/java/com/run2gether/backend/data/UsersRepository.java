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
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Repository
public class UsersRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(UsersRepository.class);

	@Transactional
	public UsersWrapper getAllUsers() {
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qusers = QUser.user;
		UsersWrapper usersWrapper = new UsersWrapper(query.from(qusers).fetch());
		return usersWrapper;
	}

	@Transactional
	public void post(User newUser) {
		newUser.setCreationDate(new LocalDateTime().toDate());
		newUser.setLastLogin(new LocalDateTime().toDate());
		em.persist(newUser);
	}

	@Transactional
	public UsersWrapper getEspecificUser(String idUser) {
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qu = QUser.user;
		BooleanExpression wh = QUser.user.email.eq(idUser).or(QUser.user.username.eq(idUser));
		UsersWrapper usersWrapper = new UsersWrapper(query.from(qu).where(wh).fetch());
		return usersWrapper;
	}

}
