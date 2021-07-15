package com.eprostam.first.app.ws.controllers;

import org.springframework.web.bind.annotation.*;

import com.eprostam.first.app.ws.requests.UserRequest;
import com.eprostam.first.app.ws.responses.UserResponse;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping
	public String getUser() {
		System.out.print("getUser() called");
		return "getUser() Called";
	}
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		return null;
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
