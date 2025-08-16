package com.spring.blog_app.dto;

import jakarta.validation.constraints.Email;
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
public class RegisterDto {

	@NotEmpty(message = "Name should not be empty or null.")
	@Size(min = 3, max = 20, message = "Name should be atleast 3 to 20 characters.")
	private String name;

	@NotEmpty(message = "Username should not be empty or null.")
	@Size(min = 3, max = 20, message = "Name should be atleast 3 to 20 characters.")
	private String username;

	@NotEmpty(message = "Email should not be empty or null.")
	@Email
	private String email;

	@NotEmpty(message = "Password should not be empty or null.")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
}
