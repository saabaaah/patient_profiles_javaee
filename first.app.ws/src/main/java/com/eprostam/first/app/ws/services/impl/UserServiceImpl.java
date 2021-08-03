package com.eprostam.first.app.ws.services.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eprostam.first.app.ws.entities.UserEntity;
import com.eprostam.first.app.ws.repositories.UserRepository;
import com.eprostam.first.app.ws.services.UserService;
import com.eprostam.first.app.ws.shared.UserDto;
import com.eprostam.first.app.ws.shared.Utils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// get the etity related to the introduced email
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		// get the entity related to the introduced email
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		
		// convert entity to Dto
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// get the user entity related to the used ID
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		// user found, return it 
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	
	// // // CRUD METHODS // // //

	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		// verifier si l'utilisateur existe
		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
		if(checkUser != null) 
			throw new RuntimeException("User with the email already exist!!");
		// générer l'entité de user
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		
		// encrypt password
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		// generetae User public ID
		userEntity.setUserId(utils.generateStringId(32));
		
		// save entity to DB
		UserEntity savedUser = userRepository.save(userEntity);
		
		System.out.println("savedUser : "+savedUser);
		
		// copier les données Dto à retourner
		UserDto userDto2 = new UserDto();
		BeanUtils.copyProperties(savedUser, userDto2);
		System.out.println("userDto2 : "+userDto2);
		
		return userDto2;
	}
	
	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		// Get the user Entity
		UserEntity userEntity = userRepository.findByUserId(id);
		
		// Check Data 
		if(userEntity == null) {
			throw new RuntimeException("No user with this ID found!");
		}
		
		// Edit User 
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setBirthDate(userDto.getBirthDate());
		userEntity.setGender(userDto.getGender());
		userEntity.setProfession(userDto.getProfession());
		
		// Save TO DB
		UserEntity userEntityUpdated = userRepository.save(userEntity);
		
		// return Dto
		UserDto userDtoUpdated = new UserDto();
		BeanUtils.copyProperties(userEntityUpdated, userDtoUpdated);
		return userDtoUpdated;
	}

	@Override
	public void deleteUser(String id) {
		// Get the user Entity
		UserEntity userEntity = userRepository.findByUserId(id);
		
		// Check Data 
		if(userEntity == null) {
			throw new RuntimeException("No user with this ID found!");
		}
		
		userRepository.delete(userEntity);
		
	}

}
