package com.SENG315.SpringJPA.domain.USDA;

/**
 * This is an enum for the various nutrients used in the usda database. The
 * nutrient ids do not change in the usda food api, so this enum was constructed
 * to simplify the process of storing nutrient values and retreiving them.
 * <ul>
 * <li>Carbs are represented by the nutrient id of 1005.</li>
 * <li>Protein is represented by the nutrient id of 1003.</li>
 * <li>Fats are represented by the nutrient id of 1004.</li>
 * <li>Calories are represented by the nutrient id of 1008.</li>
 * </ul>
 */
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
