package com.run2gether.backend.rest;

import java.util.ArrayList;

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

import com.run2gether.backend.data.FriendRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activity;
import com.run2gether.backend.model.Friend;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.UserRequest;
import com.run2gether.backend.model.wrappers.FriendWrapper;

@Path("/friend")
@Component
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;
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

	@RolesAllowed("USER")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public Response getActivity(@PathParam("username") String username) {
		Response result = Response.status(400).build();
		try {
			User user = userstRepository.get(username).getUser().get(0);
			FriendWrapper listfriend = friendRepository.get(user);
			ArrayList<UserRequest> friendUser = new ArrayList<UserRequest>();
			for (Friend friend : listfriend.getFriend())
				friendUser.add(new UserRequest(
						userstRepository.get(friend.getUserByIdFriend().getUsername()).getUser().get(0)));
			result = Response.ok(friendUser).build();
		} catch (Exception e) {
			result = Response.status(404).build();
		}
		return result;
	}

	@RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{newfriend}")
	public Response addNewCheckpoint(@PathParam("username") String username,
			@PathParam("newfriend") String usernamefriend) {
		Response result = Response.status(400).build();
		try {
			User user = userstRepository.get(username).getUser().get(0);
			User friend = userstRepository.get(usernamefriend).getUser().get(0);
			Friend newFriend = new Friend();
			newFriend.setStatus("pending");
			friendRepository.post(newFriend, user, friend);
			result = Response.ok().build();

		} catch (Exception e) {
			result = Response.status(404).build();
		}

		return result;
	}

	@RolesAllowed("USER")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{newfriend}")
	public Response modifyCheckpoint(@PathParam("username") String username,
			@PathParam("newfriend") String usernamefriend, Friend modifyFriend) {
		Response result = Response.status(400).build();
		User user;
		User myFriend;
		try {
			user = userstRepository.get(username).getUser().get(0);
			myFriend = userstRepository.get(usernamefriend).getUser().get(0);
			Friend userFrined = friendRepository.get(user, myFriend);
			userFrined.update(modifyFriend);
			friendRepository.put(userFrined);
			result = Response.ok().build();
		} catch (Exception e) {
			result = Response.status(404).build();
		}
		return result;
	}
}
