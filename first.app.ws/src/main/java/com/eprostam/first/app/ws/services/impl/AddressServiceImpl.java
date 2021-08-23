package com.eprostam.first.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprostam.first.app.ws.entities.AddressEntity;
import com.eprostam.first.app.ws.repositories.AddressRepository;
import com.eprostam.first.app.ws.services.AddressService;
import com.eprostam.first.app.ws.shared.AddressDto;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	@Override
	public List<AddressDto> getAllAddresses() {
		// get all adddresses via repository
		List<AddressEntity> addressEntities =  (List<AddressEntity>) addressRepository.findAll();
		
		// convert to addressDto
		Type typeList = new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressDtos = new ModelMapper().map(addressEntities, typeList); 
		
		return addressDtos;
	}

}
