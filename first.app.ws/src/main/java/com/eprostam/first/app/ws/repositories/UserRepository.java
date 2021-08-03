package com.eprostam.first.app.ws.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.eprostam.first.app.ws.entities.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

}
