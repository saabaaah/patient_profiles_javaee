package com.eprostam.first.app.ws.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eprostam.first.app.ws.responses.AddressResponse;
import com.eprostam.first.app.ws.services.AddressService;
import com.eprostam.first.app.ws.shared.AddressDto;


@RestController
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAddresses(Principal principal) {
		
		// get from service the addresses
		List<AddressDto> addressDtos = addressService.getAllAddresses(principal.getName());
		
		// convert to AddressResponses
		Type type = new TypeToken<List<AddressResponse>>() {}.getType();
		List<AddressResponse> addressResponses = new ModelMapper().map(addressDtos, type);
		
		return new ResponseEntity<List<AddressResponse>>(addressResponses, HttpStatus.OK);
		
	}

}
