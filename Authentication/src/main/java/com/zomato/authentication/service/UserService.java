package com.zomato.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zomato.authentication.beans.User;
import com.zomato.authentication.dao.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Register a new user after validating the input.
	 */
	public User registerUser(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Username already exists!");
		}

		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email already registered!");
		}

		// Encode the password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/**
	 * Authenticate a user by username and password.
	 */
	public Optional<User> authenticate(String username, String rawPassword) {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
			return user;
		}

		// Optional.empty() if authentication fails
		return Optional.empty();
	}
}
