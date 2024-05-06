package com.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.auth.AuthenticationRequest;
import com.springsecurity.auth.AuthenticationResponse;
import com.springsecurity.entity.RegisterRequest;
import com.springsecurity.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> 
	register(@RequestBody RegisterRequest registerRequest)
	{
		System.out.println("Inside Controller method");
		AuthenticationResponse authResponse=authService.register(registerRequest);
		return ResponseEntity.ok(authResponse);
	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
       return ResponseEntity.ok(authService.authenticate(request));
    }
	

}
