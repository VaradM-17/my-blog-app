package com.spring.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog_app.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
