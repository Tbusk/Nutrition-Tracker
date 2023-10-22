package com.SENG315.SpringJPA;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageMappingController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
