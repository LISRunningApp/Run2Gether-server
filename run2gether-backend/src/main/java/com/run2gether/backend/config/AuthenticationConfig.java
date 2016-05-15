package com.run2gether.backend.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.run2gether.backend.rest.Login.Run2getherAuthentication;

public class AuthenticationConfig extends ResourceConfig {
	public AuthenticationConfig() {
		packages("com.run2gether.backend");
		register(LoggingFilter.class);
		register(Run2getherAuthentication.class);
	}
}
