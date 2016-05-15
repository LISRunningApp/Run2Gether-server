package com.run2gether.backend.rest.Login;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.StringTokenizer;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.run2gether.backend.data.UsersRepository;

public class BasicAuthentication implements Authentication {
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private String username;
	private String password;

	@Autowired
	private UsersRepository usersRepository;

	public BasicAuthentication(String authorization) {

		final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
		// Decode username and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

		// Split username and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		username = tokenizer.nextToken();
		password = tokenizer.nextToken();

	}

	@Override
	public boolean isAllowed(Set<String> rolesSet) {
		ArrayList<Hashtable<String, String>> listUsers = User();
		boolean isAllowed = false;
		for (Hashtable<String, String> i : listUsers)
			if (i.get("username").equalsIgnoreCase(username) || i.get("email").equalsIgnoreCase(username))
				// TODO La contraseña se guardara Hasheada
				if (i.get("password").equals(password)) {
				String userRole = "USER";
				if (rolesSet.contains(userRole))
				isAllowed = true;
				}
		return isAllowed;
	}

	private ArrayList<Hashtable<String, String>> User() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "run2gether_dev";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		ArrayList<Hashtable<String, String>> listUser = new ArrayList<Hashtable<String, String>>();
		try {
			Class.forName(driver).newInstance();
			java.sql.Connection conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement st = conn.createStatement();
			String query = "SELECT password, email, username  FROM  users where email= '" + username
					+ "' or username = '" + username + "'";
			ResultSet res = st.executeQuery(query);

			while (res.next()) {
				Hashtable<String, String> element = new Hashtable<String, String>();
				element.put("username", res.getString("username"));
				element.put("email", res.getString("email"));
				element.put("password", res.getString("password"));
				listUser.add(element);
			}
			res.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}

}
