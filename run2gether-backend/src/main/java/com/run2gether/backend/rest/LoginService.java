package com.run2gether.backend.rest;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Path("/login")
@Component
public class LoginService {

	Logger log = Logger.getLogger(LoginService.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postLogin(JsonObject token) {
		log.debug(token);
		return Response.ok().build();
	}

}
