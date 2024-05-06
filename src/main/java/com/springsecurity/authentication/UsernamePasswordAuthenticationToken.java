package com.springsecurity.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken{
	
	private static final long serialVersionUID=620L;
	
	private final Object principal;
	
	private Object credentials;

	public UsernamePasswordAuthenticationToken(Object principal,Object credentials) {
		super((Collection) null);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(false);
	}
	
	public UsernamePasswordAuthenticationToken(Object principal,Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(true);
	}



	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
