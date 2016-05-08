package com.run2gether.backend.rest;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Path("/login")
@Component
public class LoginService {

	Logger log = Logger.getLogger(LoginService.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postLogin(JsonObject loginParams) {

		String token = "";
		String name = "";
		String idFace = "";

		if (loginParams.values().size() != 3)
			return Response.status(Status.BAD_REQUEST).build();

		try {
			token = loginParams.get("Token").toString();
			name = loginParams.get("Name").toString();
			idFace = loginParams.get("IdFace").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

		log.debug("Token: " + token);
		log.debug("Name: " + name);
		log.debug("IdFace: " + idFace);

		return Response.ok("Logging Successful").build();
	}
}
