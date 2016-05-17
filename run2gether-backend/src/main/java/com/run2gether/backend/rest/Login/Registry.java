package com.run2gether.backend.rest.Login;

import java.util.Date;
import java.util.HashSet;

import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Activities;
import com.run2gether.backend.model.Groupactivities;
import com.run2gether.backend.model.Users;
import com.run2gether.backend.model.Usersgroupactivities;
import com.run2gether.backend.model.Userslogros;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Path("/Registry")
@Component
public class Registry {

	@Autowired
	private UsersRepository usersRepository;

	@POST
	public Response Register(JsonObject userRegister) {
		Response result = Response.status(Status.BAD_REQUEST).build();
		Users newUser;
		if (userRegister.containsKey("loginType") && userRegister.containsKey("username")
				&& userRegister.containsKey("email"))
			try {
				UsersWrapper userSearch = usersRepository
						.getEspecificUser(userRegister.get("username").toString().replaceAll("\"", ""));
				UsersWrapper emailSearch = usersRepository
						.getEspecificUser(userRegister.get("email").toString().replaceAll("\"", ""));
				if (userSearch.getUsers().isEmpty() && emailSearch.getUsers().isEmpty()) {
					// TODO la contraseña tiene que estar hasheada

					String password = "";
					String logintype = "fb";
					String type = userRegister.get("loginType").toString();
					if (type.equalsIgnoreCase("\"db\"")) {
						logintype = "db";
						if (userRegister.containsKey("password")) {
							password = userRegister.get("password").toString().replaceAll("\"", "");
							if (password.isEmpty())
								return Response.status(Status.BAD_REQUEST).build();
						} else
							return Response.status(Status.BAD_REQUEST).build();
					}

					newUser = new Users(userRegister.get("name").toString().replaceAll("\"", ""),
							userRegister.get("surname").toString().replaceAll("\"", ""),
							userRegister.get("email").toString().replaceAll("\"", ""),
							userRegister.get("username").toString().replaceAll("\"", ""), password, new Date(),
							new Date(), "disconnected", logintype, Integer.parseInt(userRegister.get("age").toString()),
							Integer.parseInt(userRegister.get("size").toString()),
							Float.parseFloat(userRegister.get("weight").toString()),
							userRegister.get("sex").toString().replaceAll("\"", ""), new HashSet<Userslogros>(),
							new HashSet<Groupactivities>(), new HashSet<Usersgroupactivities>(),
							new HashSet<Activities>());

					usersRepository.postUser(newUser);
					result = Response.ok().build();
				}
			} catch (

			Exception e) {
				return Response.status(Status.BAD_REQUEST).build();

			}
		return result;

	}

}
