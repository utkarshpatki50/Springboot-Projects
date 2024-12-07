package com.zomato.authentication.config;

import java.util.Base64;

import io.jsonwebtoken.security.Keys;

public class SecretKeyGenerator {
	public static void main(String[] args) {
		String base64Key = Base64.getEncoder()
				.encodeToString(Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded());
		System.out.println("Generated Base64 Encoded Key: " + base64Key);
	}
}