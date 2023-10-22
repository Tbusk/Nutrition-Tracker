package com.SENG315.SpringJPA.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SENG315.SpringJPA.domain.User.User;
import com.SENG315.SpringJPA.domain.User.UserRepository;

/**
 * This class contains a rest controller that can be used to view raw json data about a user or all of the users.
 */
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "{id}")
	public Optional<User> getUser(@PathVariable long id) {
		return userRepository.findById(id);
	}
}
