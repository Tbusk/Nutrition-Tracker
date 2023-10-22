package com.SENG315.SpringJPA.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, UserItemId>{

	List<Item> findByUserId(Long userId);
	List<Item> findByUserIdAndDate(Long userId, LocalDate date);
}
 