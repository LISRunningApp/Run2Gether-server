package com.run2gether.backend.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;
import com.run2gether.backend.pojo.GcmMulticastChannel;
import com.run2gether.backend.pojo.MediaStreamer;

@Controller
public class MessagingController {

	private Logger log = Logger.getLogger(MessagingController.class);
	@Autowired
	private MulticastChannelController channelController;
	@Autowired
	private UsersRepository usersRepository;
	private static final String SENDER_ID = "AIzaSyDHZlbpoZLwSsAmk2xYkui9nL6yW9qOnls";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	/**
	 * Given a FileInputStream and its metadata retrieves the upload folder path
	 * and stores the file
	 *
	 * @param fileInputStream
	 * @param fileMetaData
	 */
	public void saveAudioFile(InputStream fileInputStream, FormDataContentDisposition fileMetaData) {

		String uploadDirectory = getUploadDirectory();

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(uploadDirectory + fileMetaData.getFileName()));
			while ((read = fileInputStream.read(bytes)) != -1)
				out.write(bytes, 0, read);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again.");
		}
	}

	/**
	 * Retrieves the upload folder path
	 *
	 * @return
	 */
	public String getUploadDirectory() {
		String osName = System.getProperty("os.name");
		String uploadDirectory = "";
		log.info(osName);

		if (osName.startsWith("Windows"))
			uploadDirectory = System.getProperty("user.home") + "\\Run2Gether\\uploads\\";
		else if (osName.startsWith("Linux"))
			uploadDirectory = "/opt/tomcat/webapps/run2gether/WEB-INF/media/uploads/";
		else {
			log.error("Unrecognized server OS type");
			throw new WebApplicationException("Unrecognized server OS");
		}

		return createIfNotExistsUploadDirectory(uploadDirectory);
	}

	/**
	 * Create if not exists yet the upload directories
	 *
	 * @param uploadDirectory
	 * @return
	 */
	private String createIfNotExistsUploadDirectory(String uploadDirectory) {

		Path newDirectoryPath = Paths.get(uploadDirectory);
		if (!Files.exists(newDirectoryPath))
			try {
				Files.createDirectories(newDirectoryPath);
				log.info("Upload directory created");
			} catch (IOException e) {
				log.warn("Cannot create upload directory");
				throw new WebApplicationException("Cannot create upload directory");
			}

		return uploadDirectory;
	}

	public void registerUserToChannel(int channel, String username, String token) {
		User user = usersRepository.getUserByUniquekey(username);
		// GroupActivity activity
		int userId;
		if (user != null)
			userId = user.getId();
		else
			throw new WebApplicationException("User not found");
		channelController.registerUserToChannel(channel, userId, token);
	}

	public void sendGcmMessage(int channel, String user, String messagetext) {
		GcmMulticastChannel gcmChannel = channelController.getMulticastChannel(channel);
		int userId = usersRepository.getUserByUniquekey(user).getId();
		Map<Integer, String> channelWithoutSender = new HashMap<>(gcmChannel.getTokenMap());
		channelWithoutSender.remove(userId);
		List<String> devices = new ArrayList<>(channelWithoutSender.values());

		Sender sender = new Sender(SENDER_ID);
		Message message = new Message.Builder().priority(Message.Priority.HIGH).collapseKey("Audio").timeToLive(30)
				.addData("message", messagetext).build();

		try {
			MulticastResult result = sender.send(message, devices, 1);
			log.debug(message.toString());
			log.debug(result.toString());
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
	}

	public Map<Integer, String> getChannelContacts(int channelId) {
		return channelController.getMulticastChannel(channelId).getTokenMap();
	}

	public Response streamAudio(String audio, String range) {
		final int chunk_size = 1024 * 1024;
		final File asset = new File(audio + ".mp3");

		if (range == null) {
			StreamingOutput streamer = output -> {
				try (FileChannel inputChannel = new FileInputStream(asset).getChannel();
						WritableByteChannel outputChannel = Channels.newChannel(output);) {
					inputChannel.transferTo(0, inputChannel.size(), outputChannel);
				}
			};
			return Response.ok(streamer).status(200).header(HttpHeaders.CONTENT_LENGTH, asset.length()).build();
		}

		String[] ranges = range.split("=")[1].split("-");
		final int from = Integer.parseInt(ranges[0]);
		/**
		 * Chunk media if the range upper bound is unspecified. Chrome sends
		 * "bytes=0-"
		 */
		int to = chunk_size + from;
		if (to >= asset.length())
			to = (int) (asset.length() - 1);
		if (ranges.length == 2)
			to = Integer.parseInt(ranges[1]);

		final String responseRange = String.format("bytes %d-%d/%d", from, to, asset.length());
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(asset, "r");
			raf.seek(from);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

		final int len = to - from + 1;
		final MediaStreamer streamer = new MediaStreamer(len, raf);
		Response.ResponseBuilder res = Response.ok(streamer).status(206).header("Accept-Ranges", "bytes")
				.header("Content-Range", responseRange).header(HttpHeaders.CONTENT_LENGTH, streamer.getLenth())
				.header(HttpHeaders.LAST_MODIFIED, new Date(asset.lastModified()));

		return res.build();
	}

	public String getUsernameFromAuthorization(String auth) {
		String username = null;
		if (auth.startsWith(AUTHENTICATION_SCHEME)) {
			final String encodedUserPassword = auth.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
			// Decode username and password
			String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			username = tokenizer.nextToken();
		} else {
			String graph = null;
			try {

				String g = "https://graph.facebook.com/v2.6/me?access_token=" + auth;
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
				return null;
			}
			ArrayList<Hashtable<String, String>> fbProfile = new ArrayList<Hashtable<String, String>>();
			Hashtable<String, String> element = new Hashtable<String, String>();
			try {
				JsonReader read = Json.createReader(new StringReader(graph));
				JsonObject json = read.readObject();
				// JSONObject json = new JSONObject(graph);
				element.put("id", json.getString("id"));
				element.put("name", json.getString("name"));
				if (json.containsKey("email"))
					element.put("email", json.getString("email"));
			} catch (Exception e) {
				return null;
			}
			fbProfile.add(element);
			username = fbProfile.get(0).get("id");
		}
		return username;
	}
}