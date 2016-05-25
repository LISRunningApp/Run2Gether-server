package com.run2gether.backend.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

@Path("/messaging")
@Component
public class MessagingService {

	private Logger log = Logger.getLogger(MessagingService.class);

	private static final String SENDER_ID = "AIzaSyDHZlbpoZLwSsAmk2xYkui9nL6yW9qOnls";

	// @RolesAllowed("USER")
	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject sendAudio(JsonObject gcm_token) {
		String token = gcm_token.get("gcm-token").toString().replaceAll("\"", "");
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gcm_token;
	}

	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		String os_name = System.getProperty("os.name");
		String upload_path = "";
		log.info(os_name);

		if (os_name.startsWith("Windows"))
			upload_path = System.getProperty("user.home") + "\\Run2Gether\\uploads\\";
		else if (os_name.startsWith("Linux"))
			upload_path = "/opt/tomcat/webapps/run2gether/WEB-INF/media/uploads/";

		File file = new File(upload_path);
		if (!file.exists())
			if (file.mkdirs())
				log.info("Upload directory created");
			else
				log.warn("Cannot create upload directory");

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(upload_path + fileMetaData.getFileName()));
			while ((read = fileInputStream.read(bytes)) != -1)
				out.write(bytes, 0, read);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}
}
