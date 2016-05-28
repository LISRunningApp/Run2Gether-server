/**
 *
 */
package com.run2gether.backend.data;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.run2gether.backend.model.wrappers.UsersWrapper;

/**
 * @author Jesus
 * @version 1.0
 * @category Repository DB
 */
@Component
public class Repository {

	@Autowired
	private UsersRepository usersRepository;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void RepositoryUser() {
		try {
			UsersWrapper usr = usersRepository.get("jj");
		} catch (Exception e) {
			System.out.println("hola");
		}
		// User a = usr.getUser().get(0);
		// assertTrue(usr.getUser().get(0) == null);

	}
}
