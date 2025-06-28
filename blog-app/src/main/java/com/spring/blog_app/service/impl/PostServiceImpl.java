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
	public PostDto craetePost(PostDto postDto) {
		
		Post post = modelMapper.map(postDto, Post.class);
		Post newPost = postRepository.save(post);

		return modelMapper.map(newPost, PostDto.class);
	}

}
