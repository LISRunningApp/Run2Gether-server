package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.Users;

@Repository
public class UsersRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Users getAllUsers() {
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qu = QUser.user;
		Users users = new Users(query.from(qu).fetch());
		return users;
	}
	
	@Transactional
	public void postUser(User newUser) {
		em.persist(newUser);
	}
}
