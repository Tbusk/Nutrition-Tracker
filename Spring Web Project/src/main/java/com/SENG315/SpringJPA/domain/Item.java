package com.SENG315.SpringJPA.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user_item")
@IdClass(UserItemId.class)
public class Item {

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
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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