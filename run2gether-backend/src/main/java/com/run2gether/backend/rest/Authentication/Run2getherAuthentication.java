package com.run2gether.backend.rest.Authentication;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.run2gether.backend.rest.Authentication.Authentication._statesLogin;

@Provider
@Component
public class Run2getherAuthentication implements javax.ws.rs.container.ContainerRequestFilter {

	Logger log = Logger.getLogger(Run2getherAuthentication.class);

	@Context
	private ResourceInfo resourceInfo;

	private static Authentication auth = null;
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final String AUTHORIZATION_PROPERTY = "Authorization";

	@Override
	public void filter(ContainerRequestContext requestContext) {
		Method method = resourceInfo.getResourceMethod();
		Response res;
		// Access allowed for all
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				res = Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build();
				requestContext.abortWith(res);
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

				// If no authorization information present; block access
				if (authorization == null || authorization.isEmpty()) {
					res = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
							.build();
					requestContext.abortWith(res);
					return;
				}

				if (authorization.get(0).contains(AUTHENTICATION_SCHEME))
					auth = new BasicAuthentication(authorization.get(0));
				else
					auth = new FacebookAuthentication(authorization.get(0));

				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				try {
					_statesLogin stateAutorize = auth.isAllowed(rolesSet);
					if (stateAutorize == _statesLogin.EXPECTATION_FAILED) {
						res = Response.status(Response.Status.EXPECTATION_FAILED)
								.entity("You cannot access this resource").build();
						requestContext.abortWith(res);
						return;
					} else if (stateAutorize == _statesLogin.UNAUTHORIZED) {
						res = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
								.build();
						requestContext.abortWith(res);
						return;
					} else if (stateAutorize == _statesLogin.OK)
						log.debug("Correct authentification");
				} catch (Exception e) {
					log.debug("abort Conection");
					e.printStackTrace();
					res = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
							.build();
					requestContext.abortWith(res);
					return;

				}
			}
		}

	}
}
