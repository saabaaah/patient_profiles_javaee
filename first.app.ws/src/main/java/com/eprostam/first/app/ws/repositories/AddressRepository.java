package com.eprostam.first.app.ws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eprostam.first.app.ws.entities.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>{

	
}
