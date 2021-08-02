package com.eprostam.first.app.ws.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eprostam.first.app.ws.shared.UserDto;

public interface UserService  extends UserDetailsService{
	UserDto createUser(UserDto userDto);
	UserDto getUserByEmail(String email);
	UserDto getUserByUserId(String userId);

}
