package com.run2gether.backend.rest.services;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.run2gether.backend.data.UsersRepository;

@Path("users")
@Component
public class UsersResource {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public JsonArray getAllUsers() {
		return usersRepository.getAllUsers();
	}

}
