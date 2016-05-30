package com.run2gether.backend.pojo;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class GcmMulticastChannel {

	private BiMap<Integer, String> tokenMap;

	public GcmMulticastChannel() {
		tokenMap = HashBiMap.create();
	}

	public GcmMulticastChannel(int userId, String token) {
		tokenMap = HashBiMap.create();
		tokenMap.put(userId, token);
	}

	public GcmMulticastChannel put(int user, String token) {
		if (tokenMap.containsValue(token)) {
			int lastuser = tokenMap.inverse().get(token);
			tokenMap.remove(lastuser);
			tokenMap.put(user, token);
		} else
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

}
