package com.SENG315.SpringJPA.domain.Item;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * This is an class that represents the user_item table in the database, which stores userId, usdaItemId, meal number, quantity, and date information.  
 * The information represents an item that is logged in the journal that can be pulled up when viewing the journal for a particular date.
 */
@Entity
@Table(name = "user_item")
@IdClass(UserItemId.class)
public class Item {

	// Variables
	@Id
	@Column(name = "user_id")
	private Long userId;

	@Id
	@Column(name = "item_id")
	private Long itemId;

	@Id
	private Integer meal;

	private double quantity;

	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	// Getters and Setters

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setMeal(Integer meal) {
		this.meal = meal;
	}

	public Integer getMeal() {
		return meal;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

}
