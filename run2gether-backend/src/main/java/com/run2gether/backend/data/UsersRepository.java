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
import com.run2gether.backend.model.QUsers;
import com.run2gether.backend.model.Users;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Repository
public class UsersRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(UsersRepository.class);

	@Transactional
	public UsersWrapper getAllUsers() {
		JPQLQuery<Users> query = new JPAQuery<>(em);
		QUsers qusers = QUsers.users;
		UsersWrapper usersWrapper = new UsersWrapper(query.from(qusers).fetch());
		return usersWrapper;
	}

	@Transactional
	public void postUser(Users newUser) {
		newUser.setCreationDate(new LocalDateTime().toDate());
		newUser.setLastLogin(new LocalDateTime().toDate());
		em.persist(newUser);
	}

	@Transactional
	public UsersWrapper getEspecificUser(String idUser) {
		JPQLQuery<Users> query = new JPAQuery<>(em);
		QUsers qu = QUsers.users;
		BooleanExpression wh = QUsers.users.email.eq(idUser).or(QUsers.users.username.eq(idUser));
		UsersWrapper usersWrapper = new UsersWrapper(query.from(qu).where(wh).fetch());
		return usersWrapper;

	}
}
