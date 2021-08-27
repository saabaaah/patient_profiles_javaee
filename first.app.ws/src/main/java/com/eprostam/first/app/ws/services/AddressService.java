package com.eprostam.first.app.ws.services;

import java.util.List;


import com.eprostam.first.app.ws.shared.AddressDto;

public interface AddressService {
	
	List<AddressDto> getAllAddresses(String email);

}
