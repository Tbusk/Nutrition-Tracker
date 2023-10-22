package com.SENG315.SpringJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SENG315.SpringJPA.domain.User.User;
import com.SENG315.SpringJPA.domain.User.UserRepository;

/**
 * Controller for user registration.  
 * When a user requests to register an account, their password will be encoded and their credentials will be stored in the database
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password) {
		User user = new User();

		user.setEmail(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("USER");

		userRepository.save(user);

		return "redirect:/login";
	}
}
