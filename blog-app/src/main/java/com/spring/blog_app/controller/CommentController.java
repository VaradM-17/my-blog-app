package com.spring.blog_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog_app.dto.CommentDto;
import com.spring.blog_app.service.CommentService;

import lombok.AllArgsConstructor;

@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/posts/{id}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{id}/comments")
	public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long id) {
		return ResponseEntity.ok(commentService.getCommentsByPostId(id));
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId,
			@PathVariable("commentId") Long commentId) {
		CommentDto getComment = commentService.getCommentByPostId(postId, commentId);
		return ResponseEntity.ok(getComment);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId,
			@PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
		CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);

		return ResponseEntity.ok(updateComment);
	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId,
			@PathVariable("commentId") Long commentId) {

		commentService.deleteComment(postId, commentId);

		return ResponseEntity.ok("Comment deleted successfully..");
	}

}
