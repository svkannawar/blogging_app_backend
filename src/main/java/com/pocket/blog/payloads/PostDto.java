package com.pocket.blog.payloads;

import com.pocket.blog.entities.Category;
import com.pocket.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String title;
	
	private String content;
	
	private String image;
	
	private String dateCreated;
	
	private CategoryDto category;
	
	private UserDto user;
	
	//private String image;

}
