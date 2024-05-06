package com.springsecurity.authentication;

import javax.naming.AuthenticationException;

import org.springframework.security.core.Authentication;

public interface AuthenticationManager {

	Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
