package com.run2gether.backend.rest.Authentication;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;

public class BasicAuthentication extends Authentication {
	Logger log = Logger.getLogger(BasicAuthentication.class);

	private static final String AUTHENTICATION_SCHEME = "Basic";
	private String username;
	private String password;

	public BasicAuthentication(String authorization) {
		try {
			final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
			// Decode username and password
			String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			username = tokenizer.nextToken();
			password = tokenizer.nextToken();
		} catch (Exception e) {
			throw new RuntimeException("Incorrecte send");
		}
	}

	@Override
	public _statesLogin isAllowed(Set<String> rolesSet) {
		ArrayList<Hashtable<String, String>> listUsers = ConnectServer(username);
		_statesLogin isAllowed = _statesLogin.EXPECTATION_FAILED;
		for (Hashtable<String, String> i : listUsers)
			if (i.get("username").equalsIgnoreCase(username) || i.get("email").equalsIgnoreCase(username))
				// TODO La contraseña se guardara Hasheada
				if (i.get("password").equals(password)) {
				String userRole = "USER";
				if (rolesSet.contains(userRole))
				isAllowed = _statesLogin.OK;
				} else
				isAllowed = _statesLogin.UNAUTHORIZED;
		return isAllowed;
	}

}
