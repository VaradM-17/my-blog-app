package com.spring.blog_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.entity.Post;
import com.spring.blog_app.exception.ResourceNotFoundException;
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

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPost(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(Long id, PostDto postDto) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		postRepository.save(post);

		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
		
		postRepository.deleteById(id);
	}

}
