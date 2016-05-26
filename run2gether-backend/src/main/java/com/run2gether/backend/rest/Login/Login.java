package com.run2gether.backend.rest.Login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;
import com.run2gether.backend.model.wrappers.UsersWrapper;

@Path("/login")
@Component
public class Login {
	Logger log = Logger.getLogger(Login.class);
	private static final String AUTHENTICATION_SCHEME = "Basic";

	private String username;
	private String password;

	@Autowired
	private UsersRepository usersRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response LoginDB(JsonObject loginParams) {
		Response result = Response.status(417).build();
		try {
			if (loginParams.values().size() == 2) {
				username = loginParams.getString("username").toString();
				password = loginParams.getString("password").toString();
				UsersWrapper wListUser = usersRepository.get(username);
				for (User i : wListUser.getUser())
					if (i.getEmail().equalsIgnoreCase(username) || i.getUsername().equalsIgnoreCase(username))
						if (i.getPassword().equalsIgnoreCase(password)) {
							String token = username + ":" + password;
							String encodedUserPassword = new String(Base64.getEncoder().encode(token.getBytes()));
							String authorization = AUTHENTICATION_SCHEME.concat(" ").concat(encodedUserPassword);
							result = Response.ok(authorization).build();
						} else
							result = Response.status(401).build();

			} else
				result = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
		return result;
	}

	@Path("/facebook")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response LoginFb(JsonObject loginParams) {
		Response result = Response.status(417).build();
		String token = loginParams.getString("token").toString();
		String idQuery = loginParams.getString("id").toString();
		String idFacebook;
		String graph = null;
		try {
			String g = "https://graph.facebook.com/v2.6/me?access_token=" + token;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// la peticion html que me envia facebook paso al json y leo de ahi
			// los datos
			JsonReader read = Json.createReader(new StringReader(graph));
			JsonObject json = read.readObject();
			idFacebook = json.getString("id");
		} catch (Exception e) {
			return Response.status(400).build();
		}
		if (idFacebook.equalsIgnoreCase(idQuery)) {
			UsersWrapper wListUser = usersRepository.get(idQuery);
			for (User i : wListUser.getUser())
				if (i.getUsername().equalsIgnoreCase(idQuery))
					result = Response.status(200).build();
				else
					result = Response.status(401).build();
		} else
			result = Response.status(401).build();
		return result;
	}

}
