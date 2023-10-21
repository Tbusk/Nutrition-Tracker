package com.SENG315.SpringJPA.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
		
		return http
		        .authorizeHttpRequests(authz -> authz
		        		.requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
		        		.requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
		        		.requestMatchers("/journal", "/foods").hasAnyRole("USER","ADMIN")
		        		.requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("USER","ADMIN")
		        		.requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("USER","ADMIN")
		        		.requestMatchers("/assets/**", "/", "/register", "/assets/vendor/jquery/**").permitAll()
		            .anyRequest().authenticated()
		        )
		        .formLogin(form -> form
		            .loginPage("/login")
		            .permitAll()
		        )
		        .logout(logout -> logout
		        		.logoutUrl("/logout")
		        		.logoutSuccessUrl("/login?logout")
		        		.invalidateHttpSession(true)
		        		.deleteCookies("JSESSIONID")
		        		)
		        .exceptionHandling(e -> e
		        .accessDeniedHandler(accessDeniedHandler())
		        )
		        .build();
	}
	
}  
