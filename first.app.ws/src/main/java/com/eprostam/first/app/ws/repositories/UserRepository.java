package com.eprostam.first.app.ws.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.eprostam.first.app.ws.entities.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	
	@Query(value = "select * from users where gender=?1", nativeQuery = true)
	Page<UserEntity> findUsersByGender(Pageable pageableRequest, int gender);

	@Query(value = "select * from users where (first_name like %?1% OR last_name like %?1%) OR (emailVerificationStatus = ?2 )", nativeQuery = true)
	Page<UserEntity> findUsersByCriteria(Pageable pageable,@Param("search") String search, int status);

}
