package com.SENG315.SpringJPA.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.SENG315.SpringJPA.domain.USDAFood;
import com.SENG315.SpringJPA.domain.USDASearchResponse;
import com.SENG315.SpringJPA.domain.User;
import com.SENG315.SpringJPA.domain.UserRepository;

@Controller
public class FoodViewController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${usda.api.key}")
	private String xAPIKey;
	
	@GetMapping("/foods")
	public String getFoods(@RequestParam(defaultValue = "") String query, Model model) { 
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<USDAFood> foods = new ArrayList<>();
		
		if(principal instanceof UserDetails) {
			String email = ((UserDetails)principal).getUsername();
			User user = userRepository.findByEmail(email);
			model.addAttribute("user", user);
		}
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Api-Key", xAPIKey);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		try {
		ResponseEntity<USDASearchResponse> response = restTemplate.exchange 
				("https://api.nal.usda.gov/fdc/v1/foods/search?pageSize=25&query=" + query, HttpMethod.GET, entity, USDASearchResponse.class);
						
	    foods.addAll(response.getBody().getFoods());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	model.addAttribute("foods", foods);	
		
	return "foods"; 
	}

}
