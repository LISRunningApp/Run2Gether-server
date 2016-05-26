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
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Path("/register")
@Component
public class Registry {

	@Autowired
	private UsersRepository usersRepository;

	@POST
	public Response Register(JsonObject userRegister) {
		Response result = Response.status(Status.BAD_REQUEST).build();
		User newUser;
		if (userRegister.containsKey("loginType") && userRegister.containsKey("username")
				&& userRegister.containsKey("email"))
			try {
				UsersWrapper userSearch = usersRepository
						.get(userRegister.get("username").toString().replaceAll("\"", ""));
				UsersWrapper emailSearch = usersRepository
						.get(userRegister.get("email").toString().replaceAll("\"", ""));
				if (userSearch.getUser().isEmpty() && emailSearch.getUser().isEmpty()) {
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

					newUser = new User(userRegister.get("name").toString().replaceAll("\"", ""),
							userRegister.get("surname").toString().replaceAll("\"", ""),
							userRegister.get("email").toString().replaceAll("\"", ""),
							userRegister.get("username").toString().replaceAll("\"", ""), password, new Date(),
							new Date(), "disconnected", logintype, Integer.parseInt(userRegister.get("age").toString()),
							Integer.parseInt(userRegister.get("size").toString()),
							Float.parseFloat(userRegister.get("weight").toString()),
							userRegister.get("sex").toString().replaceAll("\"", ""), new Date());

					usersRepository.post(newUser);
					result = Response.ok().build();
				}
			} catch (

			Exception e) {
				return Response.status(Status.BAD_REQUEST).build();

			}
		return result;

	}

}
