package com.spring.blog_app.service;

import com.spring.blog_app.dto.LoginDto;
import com.spring.blog_app.dto.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
