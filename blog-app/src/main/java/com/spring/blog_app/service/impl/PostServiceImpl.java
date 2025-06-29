package com.spring.blog_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.SortDirection;
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

	final private PostRepository postRepository;
	final private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post newPost = postRepository.save(modelMapper.map(postDto, Post.class));

		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Page<Post> posts = postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
		List<Post> listOfPosts = posts.getContent();

		List<PostDto> content = listOfPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());

		return postResponse;
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
