package com.SENG315.SpringJPA.domain;

import java.util.ArrayList;
import java.util.List;

public class USDAFood {

	private int fdcId;
	private String description;
	private String brandName;
	private String ingredients;
	private String foodCategory;
	private List<USDAFoodNutrient> foodNutrients;

	public USDAFood() {
		description = "N/A";
		brandName = "N/A";
		ingredients = "N/A";
		foodCategory = "N/A";
	}

	public Double getNutrientValue(int nutrientId) {
		for (USDAFoodNutrient nutrient : foodNutrients) {
			if (nutrient.getNutrientId() == nutrientId) {
				return nutrient.getValue();
			}
		}
		return 0.0;
	}

	public int getFdcId() {
		return fdcId;
	}

	public void setFdcId(int fdcId) {
		this.fdcId = fdcId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public List<USDAFoodNutrient> getFoodNutrients() {
		return foodNutrients;
	}

	public void setFoodNutrients(List<USDAFoodNutrient> foodNutrients) {
		List<USDAFoodNutrient> filteredNutrients = new ArrayList<>();
		for (USDAFoodNutrient nutrient : foodNutrients) {
			if (nutrient.getNutrientId() == Nutrient.CARBS.getId()) {
				filteredNutrients.add(nutrient);
			} else if (nutrient.getNutrientId() == Nutrient.PROTEIN.getId()) {
				filteredNutrients.add(nutrient);
			} else if (nutrient.getNutrientId() == Nutrient.TOTAL_FAT.getId()) {
				filteredNutrients.add(nutrient);
			} else if (nutrient.getNutrientId() == Nutrient.CALORIES.getId()) {
				filteredNutrients.add(nutrient);
			}
		}

		this.foodNutrients = filteredNutrients;
	}

}
