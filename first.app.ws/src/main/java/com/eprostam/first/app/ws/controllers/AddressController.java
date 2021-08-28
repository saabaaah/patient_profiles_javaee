package com.eprostam.first.app.ws.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprostam.first.app.ws.requests.AddressRequest;
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
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest, Principal principal){
		
		// convert request to call service
		ModelMapper modelMapper = new ModelMapper();
		AddressDto addressDto = modelMapper .map(addressRequest, AddressDto.class);
		AddressDto createdAddressDto = addressService.createAddress(addressDto, principal.getName());
		
		// convert to response & return 
		AddressResponse addressResponse = modelMapper.map(createdAddressDto, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/{id}")
	ResponseEntity<AddressResponse> getAddress(@PathVariable String id){
		
		// find the address and return it
		AddressDto addressDto = addressService.getAddressByAddressId(id);
		AddressResponse response = (new ModelMapper()).map(addressDto, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse> (response, HttpStatus.OK);
		
	}

}
