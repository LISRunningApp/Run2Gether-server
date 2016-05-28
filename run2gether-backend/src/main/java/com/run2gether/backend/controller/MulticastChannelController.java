package com.run2gether.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.WebApplicationException;

import org.springframework.stereotype.Component;

import com.run2gether.backend.pojo.GcmMulticastChannel;

@Component
public class MulticastChannelController {

	private Map<Integer, GcmMulticastChannel> channelMap;

	@PostConstruct
	public void init() {
		channelMap = new HashMap<>();
	}

	public void addMulticastChannel(int activityId, int userId, String token) {
		channelMap.put(activityId, new GcmMulticastChannel(userId, token));
	}

	public GcmMulticastChannel getMulticastChannel(int channel) {
		if (channelMap.containsKey(channel))
			return channelMap.get(channel);
		throw new WebApplicationException("Channel doesn't exist");
	}

	public GcmMulticastChannel registerUserToChannel(int activityId, int userId, String token) {
		if (!channelMap.containsKey(activityId))
			channelMap.put(activityId, new GcmMulticastChannel(userId, token));
		else
			channelMap.get(activityId).put(userId, token);
		return getMulticastChannel(activityId);
	}

}
