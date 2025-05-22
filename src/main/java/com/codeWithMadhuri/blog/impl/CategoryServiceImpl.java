package com.codeWithMadhuri.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.exceptions.ResourceNotFoundException;
import com.codeWithMadhuri.blog.payloads.CategoryDto;
import com.codeWithMadhuri.blog.repositories.CategoryRepository;
import com.codeWithMadhuri.blog.services.CategoryServiceI;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category createdCat = this.categoryRepository.save(cat);

		return this.modelMapper.map(createdCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		 Category updatedCategory=   this.categoryRepository.save(cat);
		return  this.modelMapper.map(updatedCategory, CategoryDto.class) ;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepository.delete(cat);

	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category>  categories=this.categoryRepository.findAll();
	List<CategoryDto> categoriesList =	categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoriesList ;
	}

}
