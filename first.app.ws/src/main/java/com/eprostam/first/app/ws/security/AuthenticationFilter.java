package com.eprostam.first.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eprostam.first.app.ws.SpringApplicationContext;
import com.eprostam.first.app.ws.requests.UserLoginRequest;
import com.eprostam.first.app.ws.services.UserService;
import com.eprostam.first.app.ws.shared.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	AuthenticationManager authenticationManager;
	
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		//super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("attemptAuthentication" + request);
		try {
			// getting the login request
			UserLoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), 
															loginRequest.getPassword(), 
															new ArrayList<>()
					)
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// generate token
		String token = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPERATION_TIME))
				.setSubject(((User)authResult.getPrincipal()).getUsername())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET_WORD)
				.compact();		
		// get user ID
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userDto = userService.getUser(((User)authResult.getPrincipal()).getUsername());
				
		// load headers 
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+ token);
		response.addHeader("user_id", userDto.getUserId());
	}
	
	

}
