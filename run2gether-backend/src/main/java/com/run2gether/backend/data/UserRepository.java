package com.run2gether.backend.data;

import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;

@Named
public class UserRepository {
	
	public JsonArray getAllUsers() {
		return Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("name", "John")
						.add("surname", "Doe")
						.build())
				.add(Json.createObjectBuilder()
						.add("name", "Cristian")
						.add("surname", "G�mez")
						.build())
				.build();
	}
	
}
