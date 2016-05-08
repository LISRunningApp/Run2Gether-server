package com.run2gether.backend.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;
import com.wordnik.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

	private Logger log = Logger.getLogger(JAXRSConfiguration.class);

	public JAXRSConfiguration() {
		log.info("passa");
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/help");
		beanConfig.setResourcePackage("com.run2gether.backend.rest");
		beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();

		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);

		return resources;
	}

}
