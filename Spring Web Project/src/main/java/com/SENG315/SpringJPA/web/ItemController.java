package com.SENG315.SpringJPA.web;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SENG315.SpringJPA.domain.Item.Item;
import com.SENG315.SpringJPA.domain.Item.ItemRepository;
import com.SENG315.SpringJPA.domain.Item.UserItemId;
import com.SENG315.SpringJPA.domain.User.User;
import com.SENG315.SpringJPA.domain.User.UserRepository;

/**
 * A controller for the items in a user's journal with the ability to add an item to a user's journal, 
 * update the quantity of an item in the user's journal, and delete an item from the user's journal.
 * Additionally, if a user tries to add an item that is already in that meal, 
 * it will just increase the quantity by one instead of adding a new record.
 */
@Controller
@RequestMapping(path = "/api/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public String addItem(@RequestParam Map<String, String> paramMap) {

		Long itemId = Long.parseLong(paramMap.get("itemId"));
		Long userId = -1L;
		Double quantity = Double.parseDouble(paramMap.get("quantity"));
		Integer meal = Integer.parseInt(paramMap.get("meal"));
		LocalDate date = LocalDate.parse(paramMap.get("date"));

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);
			userId = user.getId();
		}

		UserItemId userItemId = new UserItemId();

		userItemId.setUserId(userId);
		userItemId.setItemId(itemId);
		userItemId.setMeal(meal);
		userItemId.setDate(date);

		// If an item exists in the database already, it'll increase the quantity by one.
		if (itemRepository.existsById(userItemId)) {

			Item existingItem = itemRepository.findById(userItemId).get();
			existingItem.setQuantity(existingItem.getQuantity() + quantity);

			itemRepository.save(existingItem);
		} else if (userId == -1) {
			System.out.println("User is not logged in. An error occurred!");
		} else {
			
			// Creates the new item and stores it in the database
			Item newItem = new Item();
			newItem.setUserId(userId);
			newItem.setItemId(itemId);
			newItem.setQuantity(quantity);
			newItem.setMeal(meal);
			newItem.setDate(date);
			itemRepository.save(newItem);
		}
		return "redirect:/journal";
	}

	// Updates the quantity of an item in the user's journal
	@PutMapping("/update")
	public String updateItem(@RequestParam Map<String, String> paramMap) {

		Long userId = -1L;
		Long itemId = Long.parseLong(paramMap.get("itemId"));
		Double quantity = Double.parseDouble(paramMap.get("quantity"));
		Integer meal = Integer.parseInt(paramMap.get("meal"));
		LocalDate date = LocalDate.parse(paramMap.get("date"));

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);
			userId = user.getId();
		}

		UserItemId userItemId = new UserItemId();

		userItemId.setUserId(userId);
		userItemId.setItemId(itemId);
		userItemId.setDate(date);
		userItemId.setMeal(meal);

		Item existingItem = itemRepository.findById(userItemId).get();

		// if the item exists in the database, the quantity will be changed.
		if (itemRepository.existsById(userItemId)) {
			existingItem.setQuantity(quantity);
			itemRepository.save(existingItem);
		}

		return "redirect:/journal?dateSelected=" + date;
	}

	// Deletes an item from the user's journal
	@DeleteMapping("/delete")
	public String deleteItem(@RequestParam Map<String, String> paramMap) {

		Long itemId = Long.parseLong(paramMap.get("itemId"));
		Long userId = -1L;
		Integer meal = Integer.parseInt(paramMap.get("meal"));
		LocalDate date = LocalDate.parse(paramMap.get("date"));

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);
			userId = user.getId();
		}

		UserItemId userItemId = new UserItemId();

		userItemId.setUserId(userId);
		userItemId.setItemId(itemId);
		userItemId.setMeal(meal);
		userItemId.setDate(date);

		// If the item exists, it'll be deleted.
		if (itemRepository.existsById(userItemId)) {

			itemRepository.deleteById(userItemId);
		}

		return "redirect:/journal?dateSelected=" + date;
	}

}
