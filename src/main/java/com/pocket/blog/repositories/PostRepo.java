package com.pocket.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocket.blog.entities.Category;
import com.pocket.blog.entities.Post;
import com.pocket.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
