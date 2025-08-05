package com.spring.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUserNameOrEmail(String username, String email);

	Optional<User> findByUserNamme(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
