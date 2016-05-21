package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.User;

@Repository
public class LoginRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(LoginRepository.class);

	@Transactional
	public void postLoginFb(String name, String idFace, String token) {
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qusers = QUser.user;
		User user = query.from(qusers).where(qusers.username.eq(idFace)).fetchOne();
		user.getAge();
	}

	@Transactional
	public void postLoginDb(String username, String passwd) {

	}
}
