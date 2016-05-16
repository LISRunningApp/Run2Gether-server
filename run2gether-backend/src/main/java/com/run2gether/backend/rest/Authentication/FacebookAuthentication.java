package com.run2gether.backend.rest.Authentication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.log4j.Logger;

public class FacebookAuthentication extends Authentication {
	Logger log = Logger.getLogger(FacebookAuthentication.class);

	private String token;

	public FacebookAuthentication(String token) {
		this.token = token;

	}

	@Override
	public boolean isAllowed(Set<String> rolesSet) {
		boolean isAllowed = false;
		ArrayList<Hashtable<String, String>> listUserFbAccess = FbConectServer();
		ArrayList<Hashtable<String, String>> listUsers = ConectServer(listUserFbAccess.get(0).get("id"));
		for (Hashtable<String, String> i : listUsers)
			if (i.get("username").equalsIgnoreCase(listUserFbAccess.get(0).get("id")))
				isAllowed = true;

		return isAllowed;
	}

	private ArrayList<Hashtable<String, String>> FbConectServer() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?" + token;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
		} catch (Exception e) {
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		ArrayList<Hashtable<String, String>> fbProfile = new ArrayList<Hashtable<String, String>>();
		Hashtable<String, String> element = new Hashtable<String, String>();
		try {
			JsonReader read = Json.createReader(new StringReader(graph));
			JsonObject json = read.readObject();
			// JSONObject json = new JSONObject(graph);
			element.put("id", json.getString("id"));
			element.put("first_name", json.getString("first_name"));
			if (json.containsKey("email"))
				element.put("email", json.getString("email"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		fbProfile.add(element);
		return fbProfile;
	}

}
