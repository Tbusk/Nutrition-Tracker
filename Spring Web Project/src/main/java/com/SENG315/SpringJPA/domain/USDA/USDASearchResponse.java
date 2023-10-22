package com.SENG315.SpringJPA.domain.USDA;

import java.util.List;

/**
 * POGO class for the USDA items used in the search api.  Items are returned in arrays with the USDA food api search endpoint.
 */
public class USDASearchResponse {

	// List
	private List<USDAFood> foods;

	// Getters and Setters
	public List<USDAFood> getFoods() {
		return foods;
	}

	public void setFoods(List<USDAFood> foods) {
		this.foods = foods;
	}

}
