package com.spring.blog_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.blog_app.dto.PostDto;
import com.spring.blog_app.dto.PostResponse;
import com.spring.blog_app.service.PostService;
import com.spring.blog_app.utils.AppConstants;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("")
@AllArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private PostService postService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		PostDto newPost = postService.createPost(postDto);
		return new ResponseEntity<PostDto>(newPost, HttpStatus.CREATED);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
		PostDto post = postService.getPost(id);
		return ResponseEntity.ok(post);
	}

	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

		return ResponseEntity.ok(postService.getAllPosts(pageSize, pageNo, sortBy, sortDir));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok("Post deleted successfully...");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("update/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @Valid @RequestBody PostDto postDto) {
		PostDto updatePost = postService.updatePost(id, postDto);
		return ResponseEntity.ok(updatePost);
	}
}
