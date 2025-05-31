package com.codeWithMadhuri.blog.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.controllers.UserController;
import com.codeWithMadhuri.blog.entities.User;
import com.codeWithMadhuri.blog.exceptions.ResourceNotFoundException;
import com.codeWithMadhuri.blog.payloads.UserDto;
import com.codeWithMadhuri.blog.repositories.UserRepository;
import com.codeWithMadhuri.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	// 1 createUser
	@Override
	public UserDto createUser(UserDto userDto) {
		logger.info("createUser from UserServiceImpl  method started.");
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		logger.info("createUser from UserServiceImpl method ended.");
		return this.userToDto(savedUser);
	}

	// 2 updateUser
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		logger.info("updateUser from UserServiceImpl  method started.");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepository.save(user);
		UserDto userToDto1 = this.userToDto(updatedUser);
		logger.info("updateUser from UserServiceImpl  method ended.");
		return userToDto1;
	}

	// 3 getUserById
	@Override
	public UserDto getUserById(Integer userId) {
		logger.info("getUserById from UserServiceImpl  method started.");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		logger.info("getUserById from UserServiceImpl  method ended.");
		return this.userToDto(user);

	}

   //4 getAllUsers
	@Override
	public List<UserDto> getAllUsers() {
		logger.info("getAllUsers from UserServiceImpl  method started.");
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		logger.info("getAllUsers from UserServiceImpl  method ended.");
		return userDtos;
	}

	// 5 deleteUser
	@Override
	public void deleteUser(Integer userId) {
		logger.info("deleteUser from UserServiceImpl  method started.");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		logger.info("deleteUser from UserServiceImpl  method ended.");
		this.userRepository.delete(user);

	}
	
	

	//Dto to User
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;

	}
    //User to Dto
	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());

		return userDto;

	}

}
