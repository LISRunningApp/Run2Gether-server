package com.run2gether.backend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;
import com.run2gether.backend.pojo.GcmMulticastChannel;

@Controller
public class MessagingController {

	private Logger log = Logger.getLogger(MessagingController.class);
	@Autowired
	private MulticastChannelController channelController;
	@Autowired
	private UsersRepository usersRepository;
	private static final String SENDER_ID = "AIzaSyDHZlbpoZLwSsAmk2xYkui9nL6yW9qOnls";

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

}