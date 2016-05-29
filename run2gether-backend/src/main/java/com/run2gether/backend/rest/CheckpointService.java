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

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.ActivityRepository;
import com.run2gether.backend.data.CheckpointRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.CheckPointRequest;
import com.run2gether.backend.model.Checkpoint;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.ActivitiesWrapper;

@Path("/checkpoint")
@Component
public class CheckpointService {

	@Autowired
	private CheckpointRepository checkpointRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private UsersRepository userstRepository;

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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{grupactivityid}/{username}/{meters}")
	public Response getActivity(@PathParam("username") String username,
			@PathParam("grupactivityid") Integer idGroupActivity, @PathParam("meters") Integer meters) {

		User user = userstRepository.get(username).getUser().get(0);
		ActivitiesWrapper activityList = activityRepository.getActivityForGroupActiviry(idGroupActivity);
		CheckPointRequest upCheckpoint = new CheckPointRequest();
		CheckPointRequest downCheckpoint = new CheckPointRequest();
		for (Activity activity : activityList.getActivities())
			if (!activity.getUser().equals(user))
				for (Checkpoint e : activity.getCheckpoints())
					if (e.getId().getMeter() > meters && (upCheckpoint.getMeters() > e.getId().getMeter()
							|| !Double.isNaN(upCheckpoint.getMeters()))) {
						upCheckpoint.setMeters(e.getId().getMeter());
						upCheckpoint.setUser(activity.getUser().getUsername());
						upCheckpoint.setTimer(e.getTime());

					} else if (e.getId().getMeter() <= meters
							&& (downCheckpoint.getMeters() > e.getId().getMeter()
									|| !Double.isNaN(downCheckpoint.getMeters()))
							&& upCheckpoint.getUser() != activity.getUser().getUsername()) {
						downCheckpoint.setMeters(e.getId().getMeter());
						downCheckpoint.setUser(activity.getUser().getUsername());
						downCheckpoint.setTimer(e.getTime());
					}

		JSONObject json = new JSONObject();
		json.put("delante", upCheckpoint);
		json.put("detras", downCheckpoint);
		// agregar esto si da error .toJSONString()
		Response result = Response.ok(json).build();

		return result;
	}

	// @RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{activity}")
	public Response addNewCheckpoint(@PathParam("username") String username, @PathParam("activity") Integer idActivity,
			Checkpoint newCheckpoint) {
		User user = userstRepository.get(username).getUser().get(0);
		Activity activity = activityRepository.get(idActivity, user).getActivities().get(0);
		checkpointRepository.post(newCheckpoint, activity);
		return Response.ok().build();
	}

}
