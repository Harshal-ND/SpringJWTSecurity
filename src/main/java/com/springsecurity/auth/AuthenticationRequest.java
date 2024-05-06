package com.springsecurity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class AuthenticationRequest {

	// this is for logging purpose

	private String email;
	private String password;

}
