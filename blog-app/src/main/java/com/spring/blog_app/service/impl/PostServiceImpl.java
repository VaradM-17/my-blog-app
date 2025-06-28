package com.spring.blog_app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.entity.Post;
import com.spring.blog_app.repository.PostRepository;
import com.spring.blog_app.service.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto) {
		
		Post newPost = postRepository.save(modelMapper.map(postDto, Post.class));

		return modelMapper.map(newPost, PostDto.class);
	}

}
