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
	// JPQL ne fonctionne pas!!!@Query("select user from UserEntity user")
	Page<UserEntity> findUsersByGender(Pageable pageableRequest, int gender);

	// native SQL : 
	@Query(value = "select * from U where (first_name like %:search% OR last_name like %:search%) OR (emailVerificationStatus = :status )", nativeQuery = true)
	Page<UserEntity> findUsersByCriteria(Pageable pageable, @Param("search") String search, @Param("status")  int status);

}
