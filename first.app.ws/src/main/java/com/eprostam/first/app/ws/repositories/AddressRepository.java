package com.eprostam.first.app.ws.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eprostam.first.app.ws.entities.AddressEntity;
import com.eprostam.first.app.ws.entities.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>{

	List<AddressEntity> findByUser(UserEntity userEntity);

	AddressEntity findByAddressId(String id);
	
}
