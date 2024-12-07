package com.zomato.authentication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zomato.authentication.beans.User;
import com.zomato.authentication.config.JwtUtil;
import com.zomato.authentication.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		userService.registerUser(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
		String username = credentials.get("username");
		String password = credentials.get("password");
		return userService.authenticate(username, password)
				.map(user -> ResponseEntity.ok(jwtUtil.generateToken(username)))
				.orElse(ResponseEntity.status(401).body("Invalid credentials"));
	}
}
