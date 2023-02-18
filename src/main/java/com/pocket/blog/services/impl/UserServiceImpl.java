package com.pocket.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocket.blog.entities.User;
import com.pocket.blog.exceptions.ResourceNotFoundException;
import com.pocket.blog.repositories.UserRepo;
import com.pocket.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User createUser(User user) {
		
		return userRepo.save(user);
	
		}

	@Override
	public User getUserById(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		System.out.println("after exception");
		
		return user;
	}

	@Override
	public User updateUser(User user, Integer userId) {
		
		User existingUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		existingUser.setEmail(user.getEmail());
		existingUser.setAbout(user.getAbout());
		existingUser.setName(user.getName());
		existingUser.setPassword(user.getPassword());
		
		return userRepo.save(existingUser);
		
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users = userRepo.findAll();

		return users;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		
		userRepo.delete(user);
		
	}

}
