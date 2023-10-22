package com.SENG315.SpringJPA.domain;

public enum Nutrient {

	CARBS(1005), PROTEIN(1003), TOTAL_FAT(1004), CALORIES(1008);

	private final int id;

	Nutrient(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
