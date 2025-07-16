package com.spring.blog_app.dto;

import java.util.Set;

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
	private String title;
	private String description;
	private String content;
	private Set<CommentDto> comment;
}
