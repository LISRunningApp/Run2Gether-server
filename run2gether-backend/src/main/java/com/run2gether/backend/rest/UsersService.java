package com.run2gether.backend.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;

@Path("/users")
@Component
public class UsersService {

	Logger log = Logger.getLogger(UsersService.class);

	@Autowired
	private UsersRepository usersRepository;

	// @RolesAllowed("USER")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		return Response.ok(usersRepository.getAllUsers()).build();
	}

	@RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewUser(User newUser) {
		return Response.ok(usersRepository.post(newUser).toString()).build();
	}

	// @RolesAllowed("USER")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response modifyUser(@PathParam("username") String username, User modifyUser) {
		Response result = Response.status(400).build();
		User user;
		try {
			user = usersRepository.get(username).getUser().get(0);
			user.update(modifyUser);
			usersRepository.put(user);
			result = Response.ok().build();
		} catch (Exception e) {
			result = Response.status(404).build();
		}
		return result;
	}

	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam(value = "username") String username) {

		return Response.ok().build();
	}

}
