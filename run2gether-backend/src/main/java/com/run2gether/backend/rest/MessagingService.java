package com.run2gether.backend.rest;

import java.io.InputStream;
import java.util.StringTokenizer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.run2gether.backend.controller.MessagingController;

@Path("/messaging")
@Service
public class MessagingService {

	private Logger log = Logger.getLogger(MessagingService.class);
	@Autowired
	private MessagingController messagingController;
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@RolesAllowed("USER")
	@Path("/register/channel/{groupActivityId}")
	@POST
	@Produces({ MediaType.TEXT_PLAIN })
	public Response registerNewGcmTokenToMulticastChannel(@HeaderParam(value = "gcm-token") String token,
			@HeaderParam(value = "Authorization") String authHeader,
			@PathParam(value = "groupActivityId") int groupActivityId) {

		final String encodedUserPassword = authHeader.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
		// Decode username and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

		// Split username and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		String username = tokenizer.nextToken();

		messagingController.registerUserToChannel(groupActivityId, username, token);

		return Response.ok("User registered").build();
	}

	@Path("/channel/{channel}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMulticastChannel(@PathParam(value = "channel") int channel) {
		return Response.ok(messagingController.getChannelContacts(channel)).build();
	}

	// @RolesAllowed("USER")
	@Path("/send/{channel}")
	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response sendPushNotification(@HeaderParam(value = "Authorization") String authHeader,
			@PathParam(value = "channel") int channel, @FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {

		messagingController.saveAudioFile(fileInputStream, fileMetaData);

		String fileurl = "http://run2gether.uab.es:8080/run2gether/api/messaging/stream/"
				+ fileMetaData.getFileName().split("\\.", -1)[0];

		final String encodedUserPassword = authHeader.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
		// Decode username and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

		// Split username and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		String username = tokenizer.nextToken();

		messagingController.sendGcmMessage(channel, username, fileurl);

		return Response.ok("Audio sent").build();
	}

	// @RolesAllowed("USER")
	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadAudioFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {

		Preconditions.checkNotNull(fileInputStream);
		messagingController.saveAudioFile(fileInputStream, fileMetaData);

		return Response.ok("Data uploaded successfully !!").build();
	}

	@GET
	@Path("/stream/{audio_file}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response streamAudioFile(@PathParam(value = "audio_file") String audioName,
			@HeaderParam("Range") String range) {

		String os_name = System.getProperty("os.name");
		String stream_path = "";

		if (os_name.startsWith("Windows"))
			stream_path = System.getProperty("user.home") + "\\Run2Gether\\uploads\\";
		else if (os_name.startsWith("Linux"))
			stream_path = "/opt/tomcat/webapps/run2gether/WEB-INF/media/uploads/";
		else {
			log.error("Unrecognized server OS type");
			throw new WebApplicationException("Unrecognized server OS");
		}

		Response res = messagingController.streamAudio(stream_path + audioName, range);

		return res;
	}
}
