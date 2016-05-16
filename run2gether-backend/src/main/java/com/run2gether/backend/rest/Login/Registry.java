package com.run2gether.backend.rest.Login;

import java.util.Date;

import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.Users;

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
				if (usersRepository.getEspecificUser(userRegister.get("username").toString()) == null
						&& usersRepository.getEspecificUser(userRegister.get("email").toString()) == null) {
					String password = userRegister.get("password").toString();

					String logintype = "db";
					if (userRegister.get("loginType").toString().equalsIgnoreCase("facebook"))
						logintype = "fb";

					newUser = new Users(userRegister.get("name").toString(), userRegister.get("surname").toString(),
							userRegister.get("email").toString(), userRegister.get("username").toString(), password,
							new Date(), new Date(), "disconnected", logintype,
							Integer.parseInt(userRegister.get("age").toString()),
							Integer.parseInt(userRegister.get("size").toString()),
							Float.parseFloat(userRegister.get("weight").toString()), userRegister.get("sex").toString(),
							null, null, null, null);

					userRegister.get("name");
					userRegister.get("surname");
					userRegister.get("email");
					userRegister.get("username");
					userRegister.get("age");
					userRegister.get("size");
					userRegister.get("weight");
					userRegister.get("sex");

					userRegister.containsKey("password");
				}
			} catch (

			Exception e) {
				return Response.status(Status.BAD_REQUEST).build();

			}
		return null;

	}

}
