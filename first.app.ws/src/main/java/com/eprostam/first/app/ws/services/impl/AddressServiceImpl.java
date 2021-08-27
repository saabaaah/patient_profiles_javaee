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
import com.eprostam.first.app.ws.shared.Utils;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Utils utils;

	@Override
	public List<AddressDto> getAllAddresses(String email) {
		
		// get current user
		UserDto currentUserDto = userService.getUserByEmail(email);
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

	@Override
	public AddressDto createAddress(AddressDto addressDto, String email) {
		
		// convert dto to entity
		ModelMapper modelMapper = new ModelMapper();
		AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
		
		// get the user and add it to the address
		UserEntity userEntity = modelMapper.map(userService.getUserByEmail(email), UserEntity.class);
		addressEntity.setUser(userEntity);
		
		// generate address id
		addressEntity.setAddressId(utils.generateStringId(30));
		
		// create the address with the given user
		AddressEntity createdAddressEntity = addressRepository.save(addressEntity);
		
		// convert returned result
		return modelMapper.map(createdAddressEntity, AddressDto.class);
	}

}
