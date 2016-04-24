package com.run2gether.backend.data;

import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.run2gether.backend.model.Users;

@Repository
public class UsersRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public JsonArray getAllUsers() {
		Users bob = new Users("Bob", "Kerman");
		em.persist(bob);
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
