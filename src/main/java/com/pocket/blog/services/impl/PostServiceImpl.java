package com.pocket.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocket.blog.entities.Category;
import com.pocket.blog.entities.Post;
import com.pocket.blog.entities.User;
import com.pocket.blog.exceptions.ResourceNotFoundException;
import com.pocket.blog.payloads.PostDto;
import com.pocket.blog.repositories.CategoryRepo;
import com.pocket.blog.repositories.PostRepo;
import com.pocket.blog.repositories.UserRepo;
import com.pocket.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		Post post = modelMapper.map(postDto, Post.class);
		
		post.setImage("photo.png");
		
		post.setDateCreated(new Date());
		
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		post.setUser(user);
		
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		post.setCategory(category);
		
		
		Post saved = postRepo.save(post);
		
		return modelMapper.map(saved, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		
		Post updatedPost = postRepo.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getAPost(Integer postId) {
		
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = postRepo.findAll();
		List<PostDto> postsDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postsDtos;
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		
		postRepo.delete(post);
		
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		List<Post> posts = postRepo.findByCategory(cat);
		
		List<PostDto> postsDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postsDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
	
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		List<Post> posts = postRepo.findByUser(user);
		
		List<PostDto> postsDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postsDtos;
	}

}
