package com.run2gether.backend.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.ActivityRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;

@Path("/activities")
@Component
public class ActivitiesService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UsersRepository usersRepository;

	@RolesAllowed("USER")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}/{date}")
	public Activity getActivity(@PathParam("username") String username, @PathParam("date") String date) {
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		// Date dateQuerry = formatter.parse(date);

		return null;
	}

	// @RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response postUser(@PathParam("username") String username, Activity newActivity) {
		activityRepository.post(newActivity, usersRepository.get(username).getUser().get(0));
		// retronar el id de actividades
		return Response.ok().build();
	}

}
