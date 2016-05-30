package com.run2gether.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.run2gether.backend.data.UsersRepository;
import com.run2gether.backend.model.User;

@Controller
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	public void deleteUser(String username) {
		User user = usersRepository.getUserByUniquekey(username);
		if (user != null)
			usersRepository.delete(user);
	}
}
