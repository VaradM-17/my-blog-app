package com.spring.blog_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	@NotEmpty(message = "Username or email should not be empty")
	@Size(min = 3, max = 50, message = "Username or email must be between 3 and 50 characters")
	private String usernameOrEmail;

	@NotEmpty(message = "Passoword should not be empty")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
}
