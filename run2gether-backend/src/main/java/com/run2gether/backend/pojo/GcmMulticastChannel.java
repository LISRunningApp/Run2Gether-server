package com.run2gether.backend.pojo;

import java.util.HashMap;
import java.util.Map;

public class GcmMulticastChannel {

	private Map<Integer, String> tokenMap;

	public GcmMulticastChannel() {
		tokenMap = new HashMap<>();
	}

	public GcmMulticastChannel(int userId, String token) {
		tokenMap = new HashMap<>();
		tokenMap.put(userId, token);
	}

	public GcmMulticastChannel put(int user, String token) {
		tokenMap.put(user, token);
		return this;
	}

	public GcmMulticastChannel remove(int user) {
		tokenMap.remove(user);
		return this;
	}

	public String getToken(int user) {
		return tokenMap.get(user);
	}

	/**
	 * @return the tokenMap
	 */
	public Map<Integer, String> getTokenMap() {
		return tokenMap;
	}

	/**
	 * @param tokenMap
	 *            the tokenMap to set
	 */
	public void setTokenMap(Map<Integer, String> tokenMap) {
		this.tokenMap = tokenMap;
	}

}
