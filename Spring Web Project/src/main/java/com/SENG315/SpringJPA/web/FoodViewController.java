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
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.SENG315.SpringJPA.domain.USDA.USDAFood;
import com.SENG315.SpringJPA.domain.USDA.USDASearchResponse;
import com.SENG315.SpringJPA.domain.User.User;
import com.SENG315.SpringJPA.domain.User.UserRepository;

import reactor.core.publisher.Mono;

/**
 * This is a controller for the foods page to get food items from the usda food database through search. It defines what will show up on the foods page in the table.
 * This will return the searched items (default is an empty search) when a user goes to this page and will use what is in the search bar.
 */
@Controller
public class FoodViewController {

	@Autowired
	private UserRepository userRepository;

	@Value("${usda.api.key}")
	private String xAPIKey;

	@GetMapping("/foods")
	public String getFoods(@RequestParam(defaultValue = "") String query, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Used to increase max buffer size of web client due to large request
				int bufferSize = 8 * 1024 * 1024; // 8MB
				ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs()
								.maxInMemorySize(bufferSize)).build();
		
		WebClient webClient = WebClient.builder()
				.exchangeStrategies(exchangeStrategies)
				.defaultHeader("X-Api-Key",xAPIKey)
				.build();

		// Used to retrieve a users information 
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);
			model.addAttribute("user", user);
		}

		List<USDAFood> foods = new ArrayList<>();
		try {
			
			Mono<USDASearchResponse> responseMono = webClient.get()
					.uri("https://api.nal.usda.gov/fdc/v1/foods/search?pageSize=20&query=" + query)
					.retrieve()
					.bodyToMono(USDASearchResponse.class);
			
			USDASearchResponse response = responseMono.block();
			
			if(response != null && !response.getFoods().isEmpty()) {
				foods.addAll(response.getFoods());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("foods", foods);

		return "foods";
	}

}
