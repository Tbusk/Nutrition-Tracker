package com.SENG315.SpringJPA.domain.USDA;

/**
 * POGO class that represents a nutrient in the USDA food database.  
 * <ul>Contains the variables:
 * <li>Nutrient Id</li>
 * <li>Nutrient Name</li>
 * <li>Value (Ex: amount of protein, carbs, fat, etc.)</li>
 * </ul> 
 */
public class USDAFoodNutrient {

	// Variables
	private int nutrientId;
	private String nutrientName;
	private double value;

	public USDAFoodNutrient() {
		nutrientId = -1;
		nutrientName = "";
		value = 0.0d;
	}

	// Getters and Setters
	
	public int getNutrientId() {
		return nutrientId;
	}

	public void setNutrientId(int nutrientId) {
		this.nutrientId = nutrientId;
	}

	public String getNutrientName() {
		return nutrientName;
	}

	public void setNutrientName(String nutrientName) {
		this.nutrientName = nutrientName;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
