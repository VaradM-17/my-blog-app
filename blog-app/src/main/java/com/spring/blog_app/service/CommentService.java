package com.spring.blog_app.service;

import java.util.List;

import com.spring.blog_app.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commetDto);

	List<CommentDto> getCommentsByPostId(Long postId);

	CommentDto getCommentByPostId(Long postId, Long commentId);

	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

	void deleteComment(Long postId, Long commentId);
}
