package com.eprostam.first.app.ws.services;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eprostam.first.app.ws.shared.AddressDto;

public interface AddressService {
	
	List<AddressDto> getAllAddresses(Principal principal);

}
