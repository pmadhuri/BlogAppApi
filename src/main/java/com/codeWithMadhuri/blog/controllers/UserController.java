package com.codeWithMadhuri.blog.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeWithMadhuri.blog.payloads.ApiResponse;
import com.codeWithMadhuri.blog.payloads.UserDto;
import com.codeWithMadhuri.blog.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
@Autowired
private UserService userService;

//1 createUser
@PostMapping("/")
public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
	logger.info("create user method started");
	UserDto createUserDto=this.userService.createUser(userDto);
	logger.info("create user method ended");
	return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	
	
}


//2 updateUser
@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
	logger.info("update user method started");
	UserDto updatedUser= this.userService.updateUser(userDto, uid);
	logger.info("update user method ended");
	return  ResponseEntity.ok(updatedUser);
	
	
	
}


//3 deleteUser
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId ){
	logger.info("delete user method started");
	this.userService.deleteUser(userId);
	logger.info("delete user method ended");
	return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
}

//4 getAllUser

@GetMapping("/")
public ResponseEntity<List<UserDto>>  getAllUsers(){
	logger.info("getAll user method started");
	return ResponseEntity.ok(this.userService.getAllUsers());
	
	
}

//4 getSingleUser
@GetMapping("/{userId}")
public ResponseEntity<UserDto>  getSingleUser(@PathVariable Integer userId){
	logger.info("getSingle user method started");
	return ResponseEntity.ok(this.userService.getUserById(userId));
	
	
}
}
