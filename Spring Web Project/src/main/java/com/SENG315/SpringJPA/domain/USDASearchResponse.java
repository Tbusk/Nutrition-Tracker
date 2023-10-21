package com.SENG315.SpringJPA.domain;

import java.util.List;

public class USDASearchResponse {

	private List<USDAFood> foods;

	public List<USDAFood> getFoods() {
		return foods;
	}

	public void setFoods(List<USDAFood> foods) {
		this.foods = foods;
	}
	
	
}
