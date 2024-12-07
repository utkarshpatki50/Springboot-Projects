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

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public Optional<User> authenticate(String username, String rawPassword) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
			return user;
		}
		return Optional.empty();
	}
}
