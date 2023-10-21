package com.SENG315.SpringJPA.web;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.client.RestTemplate;
import com.SENG315.SpringJPA.domain.Item;
import com.SENG315.SpringJPA.domain.ItemRepository;
import com.SENG315.SpringJPA.domain.USDAFood;
import com.SENG315.SpringJPA.domain.USDASearchResponse;
import com.SENG315.SpringJPA.domain.User;
import com.SENG315.SpringJPA.domain.UserRepository;

@Controller
public class ItemViewController {
	

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${usda.api.key}")
	private String xAPIKey;
	
	@GetMapping("/journal")
	public String getItems(Model model) { 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		if(principal instanceof UserDetails) {
			try {
			String email = ((UserDetails)principal).getUsername();
			User user = userRepository.findByEmail(email);
			java.util.List<Item> items = itemRepository.findByUserId(user.getId());
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Api-Key", xAPIKey);
			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			
			
			Map<Long, USDAFood> foodMap = new HashMap<>();
			double totalCal = 0.0d;
			double totalProtein = 0.0d;
			double totalCarbs = 0.0d;
			double totalFat = 0.0d;
			USDAFood food;
			for(Item item : items) {
				
				 food = new USDAFood();;
					try {
						ResponseEntity<USDASearchResponse> response = restTemplate.exchange 
								("https://api.nal.usda.gov/fdc/v1/foods/search?pageSize=1&query=" + item.getItemId(), HttpMethod.GET, entity, USDASearchResponse.class);
										
					    food = response.getBody().getFoods().get(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					foodMap.put(item.getItemId(), food);
					totalCal += food.getNutrientValue(1008) * item.getQuantity();
					totalProtein += food.getNutrientValue(1003) * item.getQuantity();
					totalCarbs += food.getNutrientValue(1005) * item.getQuantity();
					totalFat += food.getNutrientValue(1004) * item.getQuantity();
					
			}
			model.addAttribute("items", items); 
			model.addAttribute("foodMap", foodMap);
			model.addAttribute("totalCal", (Math.round(totalCal * 100)/100.0));
			model.addAttribute("totalFat", (Math.round(totalFat * 100)/100.0));
			model.addAttribute("totalCarbs", (Math.round(totalCarbs * 100)/100.0));
			model.addAttribute("totalProtein", (Math.round(totalProtein * 100)/100.0));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		} 
	return "journal"; 
	}

}