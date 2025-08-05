package com.spring.blog_app.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.blog_app.entity.User;
import com.spring.blog_app.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

		// Convert user roles into a set of GrantedAuthority
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

		// Return a Spring Security User object (contains username, password, and authorities)
		return new org.springframework.security.core.userdetails.User(usernameOrEmail, // Username or email used to
																						// login
				user.getPassword(), // Password (already encrypted)
				authorities // Roles or permissions
		);
	}
}
