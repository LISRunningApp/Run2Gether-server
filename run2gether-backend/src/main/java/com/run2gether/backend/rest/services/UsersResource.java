package com.run2gether.backend.rest.services;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UsersResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray getAllUsers() {
		return Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("name", "John")
						.add("surname", "Doe")
						.build())
				.add(Json.createObjectBuilder()
						.add("name", "Cristian")
						.add("surname", "Gómez")
						.build())
				.build();
	}

}
