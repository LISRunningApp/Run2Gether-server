package com.run2gether.backend.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;

@Path("users")
@Component
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		return Response.ok(usersRepository.getAllUsers()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUser(User newUser) {
		usersRepository.postUser(newUser);
		return Response.ok().build();
	}

}
