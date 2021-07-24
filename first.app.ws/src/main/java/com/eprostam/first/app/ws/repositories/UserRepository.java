package com.eprostam.first.app.ws.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eprostam.first.app.ws.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);

}
