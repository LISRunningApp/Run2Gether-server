package com.run2gether.backend.config;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

	final Logger log = Logger.getLogger(ObjectMapperContextResolver.class);
	final ObjectMapper mapper = new ObjectMapper();

	public ObjectMapperContextResolver() {
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		// mapper.enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
		mapper.registerModule(new JaxbAnnotationModule());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		mapper.setDateFormat(sdf);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {

		return mapper;
	}
}