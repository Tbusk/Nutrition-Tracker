package com.SENG315.SpringJPA;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A Controller that maps the login, registration, error, and home pages
 * <ul>
 * <li>Login is available at /login. </li>
 * <li>Registration is available at /register.</li>
 * <li>Error is available at /error.</li>
 * <li>The home page is available at /</li>
 * </ul>
 */
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
