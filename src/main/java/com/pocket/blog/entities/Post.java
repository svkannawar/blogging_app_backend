package com.pocket.blog.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	private String image;
	
	private String content;
	
	private String title;
	
	private Date dateCreated;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	

}
