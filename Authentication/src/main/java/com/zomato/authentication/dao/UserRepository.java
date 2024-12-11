package com.zomato.authentication.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zomato.authentication.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email); // New method for email check
}
