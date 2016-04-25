package com.run2gether.backend.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.QUsers;
import com.run2gether.backend.model.Users;

@Repository
public class UsersRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Users> getAllUsers() {
//		Users bob = new Users("Bob", "Kerman", "bobby.k@msn.com", true, new Date(Calendar.getInstance().getTimeInMillis()), "online");
//		em.persist(bob);
		JPQLQuery<Users> query = new JPAQuery<>(em);
		QUsers qu = QUsers.users;
		List<Users> list = query.from(qu).fetch();
		return list;
	}
	
	@Transactional
	public void postUser(Users newUser) {
		em.persist(newUser);
	}
}
