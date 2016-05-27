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

import com.run2gether.backend.data.GroupActivityRepository;
import com.run2gether.backend.data.UserGroupActivityRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Usergroupactivity;

@Path("/registerusergroupactivity")
@Component
public class RegisterUserGroupActivityService {

	@Autowired
	private UserGroupActivityRepository userGroupActivityRepository;

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GroupActivityRepository groupActivityRepository;

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
	@Path("/{username}/{grupactivity}")
	public Response addNewGroupActivity(@PathParam("username") String username, Usergroupactivity newGroupActivity,
			@PathParam("grupactivity") Integer groupActivity) {
		userGroupActivityRepository.post(newGroupActivity, usersRepository.get(username).getUser().get(0),
				groupActivityRepository.get(groupActivity).getGroupActivitiy().get(0));
		// retronar el id de actividades
		return Response.ok().build();
	}

}
