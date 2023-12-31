package com.SENG315.SpringJPA.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SENG315.SpringJPA.domain.User.UserRepository;

/**
 * This is a service during the authentication process for a user in the Spring Security suite.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.SENG315.SpringJPA.domain.User.User user = userRepository.findByEmail(email);

		if (user == null) {
			System.out.println("User not found!");
		}
		return new MyUserPrincipal(user);
	}

}
