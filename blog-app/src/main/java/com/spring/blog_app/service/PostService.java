package com.spring.blog_app.service;

import java.util.List;

import com.spring.blog_app.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);

	List<PostDto> getAllPost();

	PostDto getPost(Long id);

	PostDto updatePost(Long id, PostDto postDto);

	void deletePost(Long id);
}
