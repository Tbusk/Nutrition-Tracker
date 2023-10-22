package com.SENG315.SpringJPA.domain;

public class USDAFoodNutrient {

	private int nutrientId;
	private String nutrientName;
	private double value;

	public USDAFoodNutrient() {
		nutrientId = -1;
		nutrientName = "";
		value = 0.0d;
	}

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
