package com.run2gether.backend.rest.Login;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.LoginRepository;

@Component
public class FacebookAuthentication {

	Logger log = Logger.getLogger(FacebookAuthentication.class);

	@Autowired
	private LoginRepository loginRepository;

	@POST
	@Path("/facebook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postLoginFb(JsonObject loginParams) {

		String token;
		String name;
		String idFace;

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

		loginRepository.postLoginFb(name, idFace, token);

		return Response.status(Status.EXPECTATION_FAILED).build();

		// return Response.ok("Login Successful").build();
	}

	@POST
	@Path("/database")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postLoginDb(JsonObject loginParams) {

		String username;
		String password;

		if (loginParams.values().size() != 2)
			return Response.status(Status.BAD_REQUEST).build();

		try {
			username = loginParams.get("User").toString();
			password = loginParams.get("Psw").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

		log.debug(username);
		log.debug(password);

		return Response.ok("Login Successful").build();
	}
}
