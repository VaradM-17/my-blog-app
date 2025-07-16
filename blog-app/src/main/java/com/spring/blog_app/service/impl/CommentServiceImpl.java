package com.spring.blog_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.blog_app.dto.CommentDto;
import com.spring.blog_app.entity.Comment;
import com.spring.blog_app.entity.Post;
import com.spring.blog_app.exception.BlogAPIException;
import com.spring.blog_app.exception.ResourceNotFoundException;
import com.spring.blog_app.repository.CommentRepository;
import com.spring.blog_app.repository.PostRepository;
import com.spring.blog_app.service.CommentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final PostRepository postRepository;

	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;

	@Override
	public CommentDto createComment(Long postId, CommentDto commetDto) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

		Comment comment = modelMapper.map(commetDto, Comment.class);

		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);

		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);

		return comments.stream().map((comment) -> modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentByPostId(Long postId, Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}

		return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}

		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		Comment updateComment = commentRepository.save(comment);

		return modelMapper.map(updateComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}

		commentRepository.deleteById(commentId);

	}
}
