package com.pocket.blog.services;

import java.util.List;
import com.pocket.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer categoryId);
	
	PostDto getAPost(Integer postId);
	
	List<PostDto> getAllPosts();
	
	void deletePost(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	

}
