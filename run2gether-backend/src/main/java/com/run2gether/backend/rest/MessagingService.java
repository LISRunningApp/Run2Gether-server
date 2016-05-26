package com.run2gether.backend.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

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
	@Path("/send")
	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response sendPushNotification(@HeaderParam(value = "gcm-token") String token,
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {

		System.out.println(token);

		String os_name = System.getProperty("os.name");
		String upload_path = "";
		String fileurl = "";

		if (os_name.startsWith("Windows"))
			upload_path = System.getProperty("user.home") + "\\Run2Gether\\uploads\\";
		else if (os_name.startsWith("Linux"))
			upload_path = "/opt/tomcat/webapps/run2gether/WEB-INF/media/uploads/";
		else {
			log.error("Unrecognized server OS type");
			throw new WebApplicationException("Unrecognized server OS");
		}

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

		fileurl = "http://run2gether.uab.es:8080/run2gether/api/messaging/stream/"
				+ fileMetaData.getFileName().split("\\.", -1)[0];
		Sender sender = new Sender(SENDER_ID);
		Message message = new Message.Builder().priority(Message.Priority.HIGH).collapseKey("Miau").timeToLive(30)
				.addData("message", fileurl).build();
		List<String> devices = new ArrayList<>();
		devices.add(token);
		try {
			MulticastResult result = sender.send(message, devices, 1);
			System.out.println(message.toString());
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
		return Response.ok("Data uploaded successfully !!").build();
	}

	// @RolesAllowed("USER")
	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadAudioFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		String os_name = System.getProperty("os.name");
		String upload_path = "";
		log.info(os_name);

		if (os_name.startsWith("Windows"))
			upload_path = System.getProperty("user.home") + "\\Run2Gether\\uploads\\";
		else if (os_name.startsWith("Linux"))
			upload_path = "/opt/tomcat/webapps/run2gether/WEB-INF/media/uploads/";
		else {
			log.error("Unrecognized server OS type");
			throw new WebApplicationException("Unrecognized server OS");
		}

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

		final File asset = new File(stream_path + audioName + ".mp3");

		StreamingOutput streamer = output -> {
			final FileChannel inputChannel = new FileInputStream(asset).getChannel();
			final WritableByteChannel outputChannel = Channels.newChannel(output);
			try {
				inputChannel.transferTo(0, inputChannel.size(), outputChannel);
			} finally {
				// closing the channels
				inputChannel.close();
				outputChannel.close();
			}
		};

		return Response.ok(streamer).header(HttpHeaders.CONTENT_LENGTH, asset.length()).build();
	}
}
