package com.run2gether.backend.test.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.run2gether.backend.rest.UsersService;
import com.run2gether.backend.rest.Authentication.Run2getherAuthentication;

public class UsersTest extends JerseyTest {

	@Override
	protected Application configure() {
		ResourceConfig rc = new ResourceConfig(UsersService.class);
		rc.register(SpringLifecycleListener.class);
		rc.packages("com.run2gether.backend");
		rc.register(LoggingFilter.class);
		rc.register(Run2getherAuthentication.class);
		rc.register(MultiPartFeature.class);
		Logger.getLogger(UsersTest.class).info("configura");

		return rc;
	}

	@Test
	public void insertUser() {
		// User user = new User("Manolo", "Patachula", "manueh@run2gether.miau",
		// "manueh", "1234", new Date(), new Date(),
		// "disconnected", "db", 38, 3132, 2123.f, "m", new Date());
		JsonObject user = Json.createObjectBuilder()
				.add("User",
						Json.createObjectBuilder().add("name", "Manolo").add("surname", "Patachula")
								.add("email", "manueh@run2gether.miau").add("username", "manueh")
								.add("password", "1234").add("status", "disconnected").add("loginType", "db")
								.add("age", 22).add("size", 1342).add("weight", 324.f).add("sex", "m").build())
				.build();
		Entity<JsonObject> userentity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
		Response res = target("users").request().header("Authorization", "Basic ZjNycmk4ODptaWF1")
				.header("Content-Type", "application/json").accept(MediaType.APPLICATION_JSON).post(userentity);
		// assert res.getStatusInfo() == Response.Status.OK;
	}

	@Test
	public void getAllUsers() {
		JsonObject res = target("users").request().get(JsonObject.class);
	}

	@Test
	public void deleteUser() {
		Response res = target("users/manueh").request().delete();
		// assert res.getStatusInfo() == Response.Status.OK;
	}
}
