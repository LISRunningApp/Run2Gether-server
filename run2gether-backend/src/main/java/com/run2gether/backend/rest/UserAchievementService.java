package com.run2gether.backend.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.AchievementRepository;
import com.run2gether.backend.data.UserAchievementRepository;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Achievement;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.Userachievement;

@Path("/userachievement")
@Component
public class UserAchievementService {
	@Autowired
	private AchievementRepository achievementRepository;
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UserAchievementRepository usersAchievementRepository;

	// @RolesAllowed("USER")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{idachievement}")
	public Response addNewCheckpoint(@PathParam("username") String username,
			@PathParam("idachievement") Integer idAchievement, Userachievement newMedal) {
		User user = usersRepository.get(username).getUser().get(0);
		Achievement newAchievement = achievementRepository.get(idAchievement).getAchievement().get(0);
		usersAchievementRepository.post(newMedal, user, newAchievement);

		return Response.ok().build();
	}
}
