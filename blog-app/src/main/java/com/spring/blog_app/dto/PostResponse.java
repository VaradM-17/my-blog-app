package com.spring.blog_app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content; // List of posts on current page

	private int pageNo; // Current page number

	private int pageSize; // Number of posts per page

	private long totalElements; // Total posts in DB

	private int totalPages; // Total pages

	private boolean lastPage; // Is this the last page?
}
