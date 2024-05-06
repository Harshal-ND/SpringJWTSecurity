package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.springsecurity.roles.Role.ADMIN;
import static com.springsecurity.roles.Role.MEMBER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthFilter jwtAuthFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  		
		 return http
				 .csrf(AbstractHttpConfigurer::disable)
				 .authorizeHttpRequests(
				 req-> req.requestMatchers("/auth/*")
				 .permitAll()
				 .requestMatchers("/**").hasAnyRole(ADMIN.name(), MEMBER.name())
//				 .requestMatchers("/**").hasAnyRole(MEMBER.name())
				 .anyRequest()
				 .authenticated())
				 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .authenticationProvider(authenticationProvider)
				 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		 		.build();
		 
	 		
	 	}

}
