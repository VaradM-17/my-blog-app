package com.spring.blog_app.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.blog_app.dto.LoginDto;
import com.spring.blog_app.dto.RegisterDto;
import com.spring.blog_app.entity.Role;
import com.spring.blog_app.entity.User;
import com.spring.blog_app.exception.BlogAPIException;
import com.spring.blog_app.repository.RoleRepository;
import com.spring.blog_app.repository.UserRepository;
import com.spring.blog_app.security.JwtTokenProvider;
import com.spring.blog_app.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	private JwtTokenProvider jwtTokenProvider;

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {

		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exits");
		}
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exits");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role role = roleRepository.findByName("USER").get();
		roles.add(role);

		user.setRoles(roles);

		userRepository.save(user);
		return "User registered..";
	}

}
