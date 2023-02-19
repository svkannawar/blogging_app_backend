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
import com.pocket.blog.payloads.CategoryDto;
import com.pocket.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId")Integer categoryId){
		
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	 
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> categories = categoryService.getAllCategory();
		
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
	}
	
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
		
		CategoryDto category = categoryService.getCategory(categoryId);
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
		
		categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully. ", true ), HttpStatus.OK);
		
	}

}



