package com.codeWithMadhuri.blog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeWithMadhuri.blog.payloads.UserDto;
import com.codeWithMadhuri.blog.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
@Autowired
private UserService userService;
@PostMapping("/")
public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
	logger.info("create user method started");
	UserDto createUserDto=this.userService.createUser(userDto);
	logger.info("create user method ended");
	return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	
	
}
}
