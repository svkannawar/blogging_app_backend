package com.pocket.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocket.blog.entities.Category;
import com.pocket.blog.exceptions.ResourceNotFoundException;
import com.pocket.blog.payloads.CategoryDto;
import com.pocket.blog.repositories.CategoryRepo;
import com.pocket.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		
		 Category categoryCreated = categoryRepo.save(category);
		 
		 return modelMapper.map(categoryCreated, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category catUpdated = categoryRepo.save(cat);
		
		
		return modelMapper.map(catUpdated, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categories = categoryRepo.findAll();
		
		List<CategoryDto> categoriesDto = categories.stream().map((category) ->
		modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		
		return categoriesDto;
	}

}
