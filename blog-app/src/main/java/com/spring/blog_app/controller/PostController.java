package com.spring.blog_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.dto.PostResponse;
import com.spring.blog_app.service.PostService;

import lombok.AllArgsConstructor;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	// Create a new blog post
	@PostMapping("/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

//	@GetMapping("/all")
//	public ResponseEntity<List<PostDto>> getAllPosts(
//	        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo) {
//
//	    int fixedPageSize = 5; 
//	    return ResponseEntity.ok(postService.getAllPost(pageNo, fixedPageSize));
//	}

	
	// Get all blog posts with pagination support
	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		return ResponseEntity.ok(postService.getAllPost(pageNo, pageSize));
	}

	// Get a single blog post by its ID
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPost(id));
	}

	// Update an existing blog post by its ID
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
		return ResponseEntity.ok(postService.updatePost(id, postDto));
	}

	// Delete an existing blog post by its ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return new ResponseEntity<>("Post deleted successfully.", HttpStatus.OK);
	}
}
