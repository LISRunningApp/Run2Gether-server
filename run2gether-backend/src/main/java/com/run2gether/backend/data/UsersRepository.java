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
	public Integer post(User newUser) {
		newUser.setCreationDate(new LocalDateTime().toDate());
		newUser.setLastLogin(new LocalDateTime().toDate());
		em.persist(newUser);
		em.flush();
		return newUser.getId();
	}

	@Transactional
	public UsersWrapper get(String idUser) throws NullPointerException {
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qu = QUser.user;
		UsersWrapper usersWrapper = null;
		try {
			BooleanExpression wh = QUser.user.email.eq(idUser).or(QUser.user.username.eq(idUser));
			usersWrapper = new UsersWrapper(query.from(qu).where(wh).fetch());
		} catch (NullPointerException e) {
			throw new NullPointerException("User not Found");
		}
		return usersWrapper;
	}

	@Transactional
	public User getUserByUniquekey(String usernameormail) {
		JPQLQuery<User> query = new JPAQuery<>(em);
		User user = null;
		QUser qu = QUser.user;
		try {
			BooleanExpression wh = QUser.user.email.eq(usernameormail).or(QUser.user.username.eq(usernameormail));
			user = query.from(qu).where(wh).fetch().get(0);
		} catch (NullPointerException e) {
			throw new NullPointerException("User not found");
		}
		return user;
	}

	@Transactional
	public void put(User user) {
		user.setDateModified(new LocalDateTime().toDate());
		em.merge(user);
		em.flush();
	}

	@Transactional
	public void delete(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
		em.flush();
	}
}
