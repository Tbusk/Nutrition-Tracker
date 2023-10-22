package com.SENG315.SpringJPA.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.SENG315.SpringJPA.domain.Item;
import com.SENG315.SpringJPA.domain.ItemRepository;
import com.SENG315.SpringJPA.domain.Nutrient;
import com.SENG315.SpringJPA.domain.USDAFood;
import com.SENG315.SpringJPA.domain.USDASearchResponse;
import com.SENG315.SpringJPA.domain.User;
import com.SENG315.SpringJPA.domain.UserRepository;

import jakarta.websocket.server.PathParam;

@Controller
public class ItemViewController {
	

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${usda.api.key}")
	private String xAPIKey;
	
	@GetMapping("/journal")
	public String getItems(@RequestParam(value = "dateSelected", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) { 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		if(principal instanceof UserDetails) {
			try {
				
				if(date == null) {
					date = LocalDate.now();
				}
				
			String email = ((UserDetails)principal).getUsername();
			User user = userRepository.findByEmail(email);
			java.util.List<Item> items = itemRepository.findByUserIdAndDate(user.getId(), date);
			
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
					totalCal += food.getNutrientValue(Nutrient.CALORIES.getId()) * item.getQuantity();
					totalProtein += food.getNutrientValue(Nutrient.PROTEIN.getId()) * item.getQuantity();
					totalCarbs += food.getNutrientValue(Nutrient.CARBS.getId()) * item.getQuantity();
					totalFat += food.getNutrientValue(Nutrient.TOTAL_FAT.getId()) * item.getQuantity();
					
			}
			model.addAttribute("items", items); 
			model.addAttribute("foodMap", foodMap);
			model.addAttribute("totalCal", (Math.round(totalCal * 100)/100.0));
			model.addAttribute("totalFat", (Math.round(totalFat * 100)/100.0));
			model.addAttribute("totalCarbs", (Math.round(totalCarbs * 100)/100.0));
			model.addAttribute("totalProtein", (Math.round(totalProtein * 100)/100.0));
			model.addAttribute("dateSelected", date);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		} 
	return "journal"; 
	}

}
