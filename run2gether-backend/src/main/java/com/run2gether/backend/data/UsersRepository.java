package com.run2gether.backend.data;

import javax.json.Json;
import javax.json.JsonArray;

import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {
	
	public UsersRepository() {
		
	}
	
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
