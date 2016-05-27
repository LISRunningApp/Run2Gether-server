package com.run2gether.backend.rest;

import javax.annotation.security.RolesAllowed;
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

import com.run2gether.backend.data.GroupActivityRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Groupactivity;

@Path("/groupactivity")
@Component
public class GroupActivityService {

	@Autowired
	private GroupActivityRepository groupActivityRepository;

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
	public Response addNewGroupActivity(@PathParam("username") String username, Groupactivity newGroupActivity) {
		Integer idGroupActivity = groupActivityRepository.post(newGroupActivity,
				usersRepository.get(username).getUser().get(0));
		return Response.ok(idGroupActivity).build();
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
