package com.SENG315.SpringJPA.domain.Item;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface for the item records which contains a connector (UserItemId) due composite/foreign keys being used.
 * This contains all of the methods in the JpaRepository, plus two additional ones used in the application for filtering data.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UserItemId> {

	List<Item> findByUserId(Long userId);

	List<Item> findByUserIdAndDate(Long userId, LocalDate date);
}
