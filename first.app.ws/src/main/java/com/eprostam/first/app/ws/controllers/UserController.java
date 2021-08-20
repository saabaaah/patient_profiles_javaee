package com.eprostam.first.app.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eprostam.first.app.ws.exceptions.UserException;
import com.eprostam.first.app.ws.requests.UserRequest;
import com.eprostam.first.app.ws.responses.ErrorMessages;
import com.eprostam.first.app.ws.responses.UserResponse;
import com.eprostam.first.app.ws.services.UserService;
import com.eprostam.first.app.ws.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// get users
	@GetMapping
	public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "4") int limit) {
		System.out.print("getUsers() called : " + page + ", " + limit);

		// get page -1
		if(page > 0) page -= 1;
		
		// create return list
		List<UserResponse> userResponses = new ArrayList<UserResponse>();

		List<UserDto> userDtos = userService.getUsers(page, limit);

		// copy results
		for (UserDto userDto : userDtos) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);

			userResponses.add(userResponse);
		}

		return userResponses;
	}	
	
	// get female users
	@GetMapping(path="/female")
	public List<UserResponse> getFemaleUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "4") int limit) {
		System.out.print("getUsers() called : " + page + ", " + limit);

		// get page -1
		if(page > 0) page -= 1;
		
		// create return list
		List<UserResponse> userResponses = new ArrayList<UserResponse>();

		List<UserDto> userDtos = userService.getFemaleUsers(page, limit);

		// copy results
		for (UserDto userDto : userDtos) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);

			userResponses.add(userResponse);
		}

		return userResponses;
	}

	// get user with given id
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		System.out.print("getUser() called : " + id);

		// récupérer l'utilisateur
		UserDto userDto = userService.getUserByUserId(id);

		// convertir la réponse
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	
	
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws UserException {

		// check request data :
		if (userRequest.getFirstName().isEmpty() || userRequest.getLastName().isEmpty()
				|| userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		// prendre les données de la couche representation
		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userRequest, UserDto.class);	
		
		// convertir à un DTO pour la couche service
		UserDto userDto2 = userService.createUser(userDto);

		// user response à retourner
		UserResponse userResponse = mapper.map(userDto2, UserResponse.class);	

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

		// get the user
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// Update user
		UserDto userDtoUpdated = userService.updateUser(id, userDto);

		// create response object
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDtoUpdated, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {

		// delete the user
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
