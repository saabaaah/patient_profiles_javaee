package com.eprostam.first.app.ws.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.eprostam.first.app.ws.entities.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
	@Query(value = "select * from users where gender=?1", nativeQuery = true)
	Page<UserEntity> findUsersByGender(Pageable pageableRequest, int gender);

}
