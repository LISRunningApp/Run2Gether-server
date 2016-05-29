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
import com.run2gether.backend.model.Friend;
import com.run2gether.backend.model.FriendId;
import com.run2gether.backend.model.QFriend;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.FriendWrapper;

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
		newFriend.setId(new FriendId(user.getId(), friend.getId()));
		em.persist(newFriend);
		em.flush();
	}

	@Transactional
	public FriendWrapper get(User user) {
		JPQLQuery<Friend> query = new JPAQuery<>(em);
		QFriend qf = QFriend.friend;
		FriendWrapper friendWrapper = null;
		try {
			BooleanExpression wh = QFriend.friend.userByIdUser.eq(user);
			friendWrapper = new FriendWrapper(query.from(qf).where(wh).fetch());
		} catch (NullPointerException e) {
			throw new NullPointerException("User no Found");
		}
		return friendWrapper;

	}

	@Transactional
	public Friend get(User user, User friend) {
		FriendId pk = new FriendId();
		pk.setIdUser(user.getId());
		pk.setIdFriend(friend.getId());
		return em.find(Friend.class, pk);

	}

	public void put(Friend friend) {
		friend.setDateModified(new LocalDateTime().toDate());
		em.merge(friend);
		em.flush();
	}
}
