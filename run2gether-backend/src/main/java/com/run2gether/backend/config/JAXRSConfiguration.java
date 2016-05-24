package com.run2gether.backend.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.run2gether.backend.rest.MessagingService;

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

	// Logger log = Logger.getLogger(JAXRSConfiguration.class);

	// public JAXRSConfiguration() {
	// super();
	// }

	// @Override
	// public Set<Class<?>> getClasses() {
	// Set<Class<?>> s = new HashSet<Class<?>>();
	// s.add(MultiPartFeature.class);
	// return s;
	// }

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<Class<?>>();

		// Add your resources.
		resources.add(MessagingService.class);

		// Add additional features such as support for Multipart.
		resources.add(MultiPartFeature.class);

		return resources;
	}

}
