package com.pocket.blog.services;

import java.util.List;

import com.pocket.blog.entities.User;

public interface UserService {
	
	User createUser(User user);
	
	User getUserById(Integer userId);
	
	User updateUser(User user, Integer userId);
	
	List<User> getAllUsers();
	
	void deleteUser(Integer userId);
	

}
