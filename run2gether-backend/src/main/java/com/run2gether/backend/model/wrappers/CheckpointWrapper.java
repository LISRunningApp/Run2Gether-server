package com.run2gether.backend.model.wrappers;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.run2gether.backend.model.Checkpoint;

@JsonRootName(value = "checkpoint")
public class CheckpointWrapper {

	final Logger log = Logger.getLogger(CheckpointWrapper.class);
	private List<Checkpoint> checkpoint;

	public CheckpointWrapper(List<Checkpoint> checkpoint) {
		this.checkpoint = checkpoint;
	}

	@JsonValue
	public List<Checkpoint> getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(List<Checkpoint> list) {
		checkpoint = list;
	}

}
