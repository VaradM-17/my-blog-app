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
public class CommentDto {
	private Long id;

	@NotEmpty(message = "Name must not be empty or null")
	private String name;

	@NotEmpty(message = "Email must not be empty or null")
	@Email
	private String email;

	@NotEmpty(message = "Comment body must not be empty")
	@Size(min = 10, message = "Comment body must be at least 10 characters long")
	private String body;

}
