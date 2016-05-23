package com.run2gether.backend.rest;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Path("/messaging")
@Component
public class MessagingService {

	// @RolesAllowed("USER")
	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject sendAudio(JsonObject gcm_token) {
		return gcm_token;
	}
}
