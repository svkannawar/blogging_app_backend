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

import com.pocket.blog.payloads.ApiResponse;
import com.pocket.blog.payloads.PostDto;
import com.pocket.blog.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@Valid
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		
		PostDto createPost = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatePost = postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
		}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> posts = postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> posts = postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getAPost(@PathVariable Integer postId){
		
		PostDto post = postService.getAPost(postId);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> allPosts = postService.getAllPosts();
		
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		
		postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted", true), HttpStatus.OK);
	}

}
