package com.spring.blog_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.dto.PostResponse;
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
		Post post = modelMapper.map(postDto, Post.class);

		Post newPost = postRepository.save(post);

		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto getPost(Long id) {

		Post getPost = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
		return modelMapper.map(getPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listOfPosts = posts.getContent();
		List<PostDto> contents = listOfPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(contents);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());

		return postResponse;
	}

	@Override
	public void deletePost(Long id) {
		Post getPost = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

		postRepository.deleteById(id);
	}

	@Override
	public PostDto updatePost(Long id, PostDto postDto) {
		Post findPost = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

		findPost.setTitle(postDto.getTitle());
		findPost.setDescription(postDto.getDescription());
		findPost.setContent(postDto.getContent());

		postRepository.save(findPost);

		return modelMapper.map(findPost, PostDto.class);
	}
}
