package com.SENG315.SpringJPA.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class UserItemId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2298764990348959893L;
	private Long userId;
	private Long itemId;
	private Integer meal;

	@Temporal(TemporalType.DATE)
	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getMeal() {
		return meal;
	}

	public void setMeal(Integer meal) {
		this.meal = meal;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserItemId that = (UserItemId) o;
		return Objects.equals(userId, that.userId) && Objects.equals(itemId, that.itemId)
				&& Objects.equals(meal, that.meal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, itemId, meal);
	}

}