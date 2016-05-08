package com.run2gether.backend.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

	Logger log = Logger.getLogger(JAXRSConfiguration.class);

	public JAXRSConfiguration() {
		super();
	}

}
