package com.eprostam.first.app.ws.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eprostam.first.app.ws.shared.UserDto;

public interface UserService  extends UserDetailsService{
	
	// GET METHODS //
	UserDto getUserByEmail(String email);
	UserDto getUserByUserId(String userId);
	
	
	// CRUD METHODS //
	UserDto createUser(UserDto userDto);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String id);
	List<UserDto> getUsers(int page, int limit);

}
