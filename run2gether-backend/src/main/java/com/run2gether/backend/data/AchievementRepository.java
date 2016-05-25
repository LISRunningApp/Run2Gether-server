package com.run2gether.backend.data;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.run2gether.backend.model.Achievement;
import com.run2gether.backend.model.QAchievement;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.QUserachievementId;
import com.run2gether.backend.model.wrappers.AchievementWrapper;

@Repository
public class AchievementRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(AchievementRepository.class);

	@Transactional
	public AchievementWrapper getAchievement(Date date, String username) {
		JPQLQuery<Achievement> query = new JPAQuery<>(em);
		QAchievement qa = QAchievement.achievement;
		QUser qu = QUser.user;
		BooleanExpression wh = QAchievement.achievement.id.eq(QUserachievementId.userachievementId.idAchievement)
				.and(QUserachievementId.userachievementId.idUser.eq(QUser.user.id))
				.and(QUser.user.username.eq(username)).and(QAchievement.achievement.dateModified.eq(date));
		AchievementWrapper achievementWrapper = new AchievementWrapper(query.from(qa, qu).where(wh).fetch());
		return achievementWrapper;
	}

	@Transactional
	public void postAchievement(Achievement newAchievement) {
		newAchievement.setDateModified(new LocalDateTime().toDate());
		em.persist(newAchievement);
	}
}
