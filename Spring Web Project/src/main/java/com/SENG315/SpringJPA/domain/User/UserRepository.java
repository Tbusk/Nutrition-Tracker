package com.SENG315.SpringJPA.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface for the user records.  This contains all of the methods available in the JpaRepository, 
 * plus an additional one to find a user by email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
