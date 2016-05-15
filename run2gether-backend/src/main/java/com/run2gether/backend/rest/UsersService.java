package com.run2gether.backend.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Users;

@Path("/users")
@Component
public class UsersService {

	Logger log = Logger.getLogger(UsersService.class);

	@Autowired
	private UsersRepository usersRepository;

	@RolesAllowed("USER")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		return Response.ok(usersRepository.getAllUsers()).build();
	}

	@RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUser(Users newUser) {
		usersRepository.postUser(newUser);
		return Response.ok().build();
	}
}
