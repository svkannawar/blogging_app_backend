package com.pocket.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.pocket.blog.entities.User;
import com.pocket.blog.payloads.ApiResponse;
import com.pocket.blog.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		
		User usr = userService.createUser(user);
		return new ResponseEntity<>(usr, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getAUser(@PathVariable("userId") Integer userId ){
		
		User user = userService.getUserById(userId);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable("userId") Integer userId){
		
		User usr = userService.updateUser(user, userId);
		return new ResponseEntity<User>(usr, HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		
		userService.deleteUser(userId);
		//ApiResponse is the class created for custom Responses
		return new ResponseEntity(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		
		return ResponseEntity.ok(userService.getAllUsers());
		
	}
	

}
