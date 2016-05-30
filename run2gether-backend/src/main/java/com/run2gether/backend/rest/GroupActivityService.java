package com.run2gether.backend.rest;

import java.text.SimpleDateFormat;

import javax.annotation.security.RolesAllowed;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.controller.GroupActivityController;
import com.run2gether.backend.data.ActivityRepository;
import com.run2gether.backend.data.GroupActivityRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Groupactivity;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Path("/groupactivity")
@Component
public class GroupActivityService {

	@Autowired
	private GroupActivityController groupactivityController;
	@Autowired
	private GroupActivityRepository groupActivityRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ActivityRepository activityRepository;

	// @RolesAllowed("USER")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/size/{username}")
	public Response sizeGrupActivity(@PathParam("username") String username) {
		Response result = Response.status(400).build();
		try {
			User user = usersRepository.get(username).getUser().get(0);
			ActivitiesWrapper listActivity = activityRepository.get(user);
			GroupActivityController tools = new GroupActivityController();
			Response.ok(tools.numeroGroupActivity(listActivity)).build();
		} catch (Exception e) {
			result = Response.status(404).build();
		}
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response get(@PathParam("username") String username) {
		Response result = Response.status(400).build();
		try {
			User user = usersRepository.get(username).getUser().get(0);
			result = Response.ok(groupActivityRepository.getAdmin(user)).build();
		} catch (Exception e) {
			result = Response.status(404).build();
		}
		return result;
	}

	@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}/{date}")
	public Activity getGrupActivity(@PathParam("username") String username, @PathParam("date") String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		// Date dateQuerry = formatter.parse(date);

		return null;
	}

	// @RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response addNewGroupActivity(@PathParam("username") String username, Groupactivity newGroupActivity) {
		Integer idGroupActivity = groupActivityRepository.post(newGroupActivity,
				usersRepository.get(username).getUser().get(0));
		return Response.ok(idGroupActivity).build();
	}

	// @RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/full/{username}")
	public Response addNewGroupActivityFull(@PathParam("username") String username, JsonObject activity) {
		Response result = Response.status(400).build();
		Integer id = groupactivityController.creationGrouActivityAndActivity(username, activity);
		if (id != null)
			result = Response.ok(id.toString()).build();
		else
			result = Response.status(404).build();
		return result;
	}

	// @RolesAllowed("USER")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{idgroupactivity}")
	public Response modifyGroupActivity(@PathParam("idgroupactivity") Integer idgroupactivity,
			Groupactivity modifyGroupactivity) {
		Groupactivity groupActivity = groupActivityRepository.get(idgroupactivity).getGroupActivitiy().get(0);
		groupActivity.update(modifyGroupactivity);
		groupActivityRepository.put(groupActivity);
		return Response.ok().build();
	}

}
