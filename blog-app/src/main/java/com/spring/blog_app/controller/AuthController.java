package com.spring.blog_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog_app.dto.JwtAuthResponse;
import com.spring.blog_app.dto.LoginDto;
import com.spring.blog_app.dto.RegisterDto;
import com.spring.blog_app.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("")
@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

	private AuthService authService;

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
		String response = authService.login(loginDto);

		JwtAuthResponse jwtAuthReponse = new JwtAuthResponse();
		jwtAuthReponse.setAccessToken(response);

		return ResponseEntity.ok(jwtAuthReponse);
	}

	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
