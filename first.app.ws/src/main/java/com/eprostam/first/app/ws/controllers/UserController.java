package com.eprostam.first.app.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eprostam.first.app.ws.requests.UserRequest;
import com.eprostam.first.app.ws.responses.UserResponse;
import com.eprostam.first.app.ws.services.UserService;
import com.eprostam.first.app.ws.shared.UserDto;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/{id}")
	public String getUser(@PathVariable String id) {
		System.out.print("getUser() called : "+id);
		return "getUser() Called";
	}
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		
		// prendre les données de la couche representation
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userRequest, userDto);
		
		// convertir à un DTO pour la couche service
		UserDto userDto2 = userService.createUser(userDto);
		
		// user response à retourner 
		UserResponse userResponse =  new UserResponse();
		
		BeanUtils.copyProperties(userDto2, userResponse);
		
		return userResponse;
	}
	@PutMapping
	public String updateUser() {
		return "updateUser() Called";
	}
	@DeleteMapping
	public String deleteUser() {
		return "deleteUser() Called";
	}

}
