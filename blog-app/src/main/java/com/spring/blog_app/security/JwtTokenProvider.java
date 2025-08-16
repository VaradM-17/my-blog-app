package com.spring.blog_app.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.spring.blog_app.exception.BlogAPIException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	public String generateToken(Authentication authentication) {

		Date currentDate = new Date();

		Date expiryDate = new Date(currentDate.getTime() + jwtExpirationDate);

		return Jwts.builder().subject(authentication.getName()).issuedAt(currentDate).expiration(expiryDate)
				.signWith(key()).compact();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUsername(String token) {
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();

	}

	public boolean validateToken(String token) {
		try {

			Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);

			return true;

		} catch (MalformedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} 
		catch (ExpiredJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} 
		catch (UnsupportedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		} 
		catch (IllegalArgumentException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		} 
		catch (Exception ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT token validation failed");
		}
	}
}
