package com.springsecurity.service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "N0NuokWWb5XjMP+V3XLfyLkaSArwxNm17VeAvv7+y4+Y/DmxBLenvwOPO404lfl6UfyyEGgQ02ETDEPRMwV/+Q==";

	public String generateToken(UserDetails user) {
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("authorities", populateAuthorities(user.getAuthorities()))
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(getSigningKey()).compact();
	}
//need to set claims

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : authorities) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}
	private Claims extractAllClaims(String token) {
        return Jwts.parser()
        		.verifyWith((SecretKey) getSigningKey())
        		.build()
        		.parseUnsecuredClaims(token)
        		.getPayload();
    }
	
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }
	 
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	 public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()));
	    }

}
