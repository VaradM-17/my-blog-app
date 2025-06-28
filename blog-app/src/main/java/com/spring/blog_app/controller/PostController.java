package com.spring.blog_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.service.PostService;

import lombok.AllArgsConstructor;

@CrossOrigin("http://localhost:3000")
@RequestMapping("/blogs")
@AllArgsConstructor
@RestController
public class PostController {

	private PostService postService;

	@PostMapping("/new-post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
}
