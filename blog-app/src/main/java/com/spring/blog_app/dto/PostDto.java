package com.spring.blog_app.dto;

import java.util.Set;

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
public class PostDto {
	
	private Long id;

	@NotEmpty(message = "Post title must not be empty")
	@Size(min = 6, message = "Post title must be at least 6 characters long")
	private String title;

	@NotEmpty(message = "Post description must not be empty")
	@Size(min = 15, message = "Post description must be at least 15 characters long")
	private String description;

	@NotEmpty(message = "Post content must not be empty")
	private String content;

	private Set<CommentDto> comment;
}
