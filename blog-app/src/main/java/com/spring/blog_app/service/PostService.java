package com.spring.blog_app.service;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.dto.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);

	PostResponse getAllPost(int pageNo, int pageSize);

	PostDto getPost(Long id);

	PostDto updatePost(Long id, PostDto postDto);

	void deletePost(Long id);
}
