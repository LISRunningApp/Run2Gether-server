package com.run2gether.backend.model;

public class CheckPointRequest {

	private String user;
	private double timer;
	private double meters;

	public double getTimer() {
		return timer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	public double getMeters() {
		return meters;
	}

	public void setMeters(double meters) {
		this.meters = meters;
	}
}
