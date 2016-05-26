package com.run2gether.backend.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.run2gether.backend.model.Friend;
import com.run2gether.backend.model.User;

@Repository
public class FriendRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(FriendRepository.class);

	@Transactional
	public void post(Friend newFriend, User user, User friend) {
		newFriend.setDateModified(new LocalDateTime().toDate());
		newFriend.setUserByIdUser(user);
		newFriend.setUserByIdFriend(friend);
		em.persist(newFriend);
	}
}
