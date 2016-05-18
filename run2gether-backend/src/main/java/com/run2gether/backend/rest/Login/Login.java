package com.run2gether.backend.rest.Login;

import java.util.Base64;

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

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Users;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Path("/login")
@Component
public class Login {
	Logger log = Logger.getLogger(Login.class);
	private static final String AUTHENTICATION_SCHEME = "Basic";

	private String username;
	private String password;

	@Autowired
	private UsersRepository usersRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response LoginDB(JsonObject loginParams) {
		Response result = Response.status(401).build();
		try {
			if (loginParams.values().size() == 2) {
				username = loginParams.getString("username").toString();
				password = loginParams.getString("password").toString();
				UsersWrapper wListUser = usersRepository.getEspecificUser(username);
				for (Users i : wListUser.getUsers())
					if (i.getEmail().equalsIgnoreCase(username) || i.getUsername().equalsIgnoreCase(username))
						if (i.getPassword().equalsIgnoreCase(password)) {
							String token = username + ":" + password;
							String encodedUserPassword = new String(Base64.getEncoder().encode(token.getBytes()));
							String authorization = AUTHENTICATION_SCHEME.concat(" ").concat(encodedUserPassword);
							result = Response.ok(authorization).build();
						}

			} else
				result = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			return Response.status(401).build();
		}
		return result;
	}

	@Path("/facebook")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response LoginFb(JsonObject loginParams) {
		return Response.status(200).build();
	}

}
