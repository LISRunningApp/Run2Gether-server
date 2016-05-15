package com.run2gether.backend.rest.Login;

import java.util.Set;

public interface Authentication {
	public boolean isAllowed(Set<String> rolesSet);
}
