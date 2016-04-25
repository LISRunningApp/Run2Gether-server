package com.run2gether.backend.rest;

import java.util.List;

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
import com.run2gether.backend.model.Users;


@Path("users")
@Component
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Users> getAllUsers() {
		return usersRepository.getAllUsers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUser(Users newUser) {
		usersRepository.postUser(newUser);
		return Response.ok().build();
	}

}
