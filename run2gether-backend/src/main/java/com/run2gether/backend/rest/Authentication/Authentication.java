package com.run2gether.backend.rest.Authentication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

abstract public class Authentication {

	protected enum _statesLogin {
		OK, UNAUTHORIZED, EXPECTATION_FAILED
	};

	abstract public _statesLogin isAllowed(Set<String> rolesSet);

	protected ArrayList<Hashtable<String, String>> ConectServer(String username) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "run2gether_dev";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "dev";
		String password = "miaumiau";
		// String userName = "root";
		// String password = "";
		ArrayList<Hashtable<String, String>> listUser = new ArrayList<Hashtable<String, String>>();
		try {
			Class.forName(driver).newInstance();
			java.sql.Connection conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement st = conn.createStatement();
			String query = "SELECT password, email, username, login_type  FROM  users where email= '" + username
					+ "' or username = '" + username + "'";
			ResultSet res = st.executeQuery(query);

			while (res.next()) {
				Hashtable<String, String> element = new Hashtable<String, String>();
				element.put("username", res.getString("username"));
				element.put("email", res.getString("email"));
				element.put("password", res.getString("password"));
				element.put("login_type", res.getString("login_type"));
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
