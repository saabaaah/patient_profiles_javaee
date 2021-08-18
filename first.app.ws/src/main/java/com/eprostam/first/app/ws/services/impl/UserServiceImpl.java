package com.eprostam.first.app.ws.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		
		
		// check if we have the addresses set, else, we declare an empty array
		if(userDto.getAddresses() == null) {
			userDto.setAddresses(new ArrayList<>());
		}
		// add address ids if the addresses are set
		for(int i= 0 ; i < userDto.getAddresses().size(); i++) {
			userDto.getAddresses().get(i).setUser(userDto);
			userDto.getAddresses().get(i).setAddressId(utils.generateStringId(30));
		}
		
		// 
		
		// generate user entity
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);	
				
		// encrypt password
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		// generetae User public ID
		userEntity.setUserId(utils.generateStringId(32));
		
		// save entity to DB
		UserEntity savedUser = userRepository.save(userEntity);
		
		System.out.println("savedUser : "+savedUser);
		
		// copier les données Dto à retourner
		UserDto userDto2 = mapper.map(savedUser, UserDto.class);	
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

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		// list to return 
		List<UserDto> userDtos = new ArrayList<>();
		
		// get users per page
		Pageable pageable = PageRequest.of(page, limit);
		Page<UserEntity> pageUsers = userRepository.findAll(pageable);
		
		// convert results 
		for(UserEntity userEntity : pageUsers) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(userEntity, dto);
			
			userDtos.add(dto);
		}
		
		return userDtos;
	}

}
