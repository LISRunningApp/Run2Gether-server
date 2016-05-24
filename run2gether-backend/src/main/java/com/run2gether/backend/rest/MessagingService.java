package com.run2gether.backend.rest;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

@Path("/messaging")
@Component
public class MessagingService {

	private static final String SENDER_ID = "AIzaSyDHZlbpoZLwSsAmk2xYkui9nL6yW9qOnls";

	// @RolesAllowed("USER")
	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject sendAudio(JsonObject gcm_token) {
		// String url = "https://gcm-http.googleapis.com/gcm/send";
		// URL gcm;
		// HttpsURLConnection con;
		String token = gcm_token.get("gcm-token").toString().replaceAll("\"", "");
		// String messageold = "{\"to\": \"" + token + "\",\"data\":
		// {\"message\": \"Run2Gether MIAU!\"}}";
		String messageText = "A dormir!!";
		System.out.println(gcm_token);
		Sender sender = new Sender(SENDER_ID);
		Message message = new Message.Builder().collapseKey("Miau").timeToLive(30).delayWhileIdle(true)
				.addData("message", messageText).build();
		List<String> devices = new ArrayList<>();
		devices.add(token);
		try {
			MulticastResult result = sender.send(message, devices, 1);
			System.out.println(result.toString());
			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0)
					System.out.println("GUD INOF");
			} else {
				int error = result.getFailure();
				System.out.println("Broadcast failure: " + error);
			}
			// gcm = new URL(url);
			// con = (HttpsURLConnection) gcm.openConnection();
			// con.setRequestMethod("POST");
			// con.setRequestProperty("Content-Type", "application/json");
			// con.setRequestProperty("Authorization", "key=" + SENDER_ID);
			//
			// con.setDoOutput(true);
			// DataOutputStream wr = new
			// DataOutputStream(con.getOutputStream());
			// wr.writeBytes(messagetext);
			// wr.flush();
			// wr.close();
			//
			// int responseCode = con.getResponseCode();
			// System.out.println("\nSending 'POST' request to URL : " + url);
			// System.out.println("Post parameters : " + message);
			// System.out.println("Response Code : " + responseCode);
			//
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(con.getInputStream()));
			// String inputLine;
			// StringBuffer response = new StringBuffer();
			//
			// while ((inputLine = in.readLine()) != null)
			// response.append(inputLine);
			// in.close();
			//
			// // print result
			// System.out.println(response.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gcm_token;
	}
}
