package com.eprostam.first.app.ws.services.impl;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprostam.first.app.ws.entities.AddressEntity;
import com.eprostam.first.app.ws.entities.UserEntity;
import com.eprostam.first.app.ws.repositories.AddressRepository;
import com.eprostam.first.app.ws.services.AddressService;
import com.eprostam.first.app.ws.services.UserService;
import com.eprostam.first.app.ws.shared.AddressDto;
import com.eprostam.first.app.ws.shared.UserDto;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserService userService;

	@Override
	public List<AddressDto> getAllAddresses(Principal principal) {
		
		// get current user
		UserDto currentUserDto = userService.getUserByEmail(principal.getName());
		// convert user to entity
		UserEntity currentUserEntity = (new ModelMapper()).map(currentUserDto, UserEntity.class);
		
		// get all adddresses via repository
		List<AddressEntity> addressEntities = new ArrayList<>();
		
		
		// check whether the user is admin or not
		if(currentUserDto.isAdmin()) {
			addressEntities=  (List<AddressEntity>) addressRepository.findAll();
		}else {
			// user not admin, get addresses by user 
			addressEntities=  (List<AddressEntity>) addressRepository.findByUser(currentUserEntity);
		}
		
		// convert to addressDto
		Type typeList = new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressDtos = new ModelMapper().map(addressEntities, typeList); 
		
		return addressDtos;
	}

}
