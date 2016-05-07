package com.run2gether.backend.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.User;

@Repository
public class UsersRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<User> getAllUsers() {
//		User bob = new User("Bob", "Kerman", "bobby.k@msn.com", true, new Date(Calendar.getInstance().getTimeInMillis()), "online");
//		em.persist(bob);
		JPQLQuery<User> query = new JPAQuery<>(em);
		QUser qu = QUser.user;
		List<User> list = query.from(qu).fetch();
		return list;
	}
	
	@Transactional
	public void postUser(User newUser) {
		em.persist(newUser);
	}
}
