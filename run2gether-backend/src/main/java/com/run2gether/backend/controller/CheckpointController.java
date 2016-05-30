package com.run2gether.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.CheckpointRepository;
import com.run2gether.backend.model.wrappers.CheckpointWrapper;

@Component
public class CheckpointController {

	@Autowired
	private CheckpointRepository checkpointRepository;

	/**
	 * Retorna una lista con los checkpoint dado un identificador de actividad
	 *
	 * @param idactivity
	 * @return
	 * @throws Exception
	 */
	public CheckpointWrapper getcheckpointsForActivity(Integer idactivity) throws Exception {
		return checkpointRepository.getCheckpointsForIDActivity(idactivity);

	}

}
