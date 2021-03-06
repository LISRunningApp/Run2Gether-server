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
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Checkpoint;
import com.run2gether.backend.model.QCheckpoint;
import com.run2gether.backend.model.QUser;
import com.run2gether.backend.model.wrappers.CheckpointWrapper;

@Repository
public class CheckpointRepository {

	@PersistenceContext
	private EntityManager em;

	final Logger log = Logger.getLogger(CheckpointRepository.class);

	@Transactional

	public CheckpointWrapper getCheckpoints(Date date, String username) {
		JPQLQuery<Checkpoint> query = new JPAQuery<>(em);
		QCheckpoint qc = QCheckpoint.checkpoint;
		QUser qu = QUser.user;
		BooleanExpression wh = QCheckpoint.checkpoint.activity.user.username.eq(username)
				.and(QCheckpoint.checkpoint.dateModified.eq(date));
		CheckpointWrapper checkpointWrapper = new CheckpointWrapper(query.from(qu, qc).where(wh).fetch());
		return checkpointWrapper;
	}

	@Transactional
	public void post(Checkpoint newCheckpoint, Activity activity) {
		newCheckpoint.setDateModified(new LocalDateTime().toDate());
		newCheckpoint.setActivity(activity);
		newCheckpoint.getId().setIdActivity(activity.getId());
		em.persist(newCheckpoint);
		em.flush();
	}

	public CheckpointWrapper getCheckpointsForIDActivity(int idactivity) {
		try {

			JPQLQuery<Checkpoint> query = new JPAQuery<>(em);
			QCheckpoint qc = QCheckpoint.checkpoint;
			BooleanExpression wh = QCheckpoint.checkpoint.activity.id.eq(idactivity);
			CheckpointWrapper checkpointWrapper = new CheckpointWrapper(query.from(qc).where(wh).fetch());
			return checkpointWrapper;

		} catch (Exception e) {

		}
		return null;
	}

}
