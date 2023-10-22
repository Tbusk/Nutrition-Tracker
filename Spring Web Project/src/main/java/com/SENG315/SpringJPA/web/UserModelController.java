package com.SENG315.SpringJPA.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SENG315.SpringJPA.domain.User.User;
import com.SENG315.SpringJPA.domain.User.UserRepository;

@Controller
@RequestMapping(path = "/admin")
public class UserModelController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "./admin";
	}

	@GetMapping(path = "/user/{id}")
	public String getUser(@PathVariable long id, Model model) {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			model.addAttribute("user", user.get());
		}

		return "./admin";
	}

	@DeleteMapping(path = "/delete/user/{id}")
	public String deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
		return "redirect:/admin";
	}

}
