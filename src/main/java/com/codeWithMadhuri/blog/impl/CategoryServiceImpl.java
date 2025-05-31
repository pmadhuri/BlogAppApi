package com.codeWithMadhuri.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.controllers.UserController;
import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.exceptions.ResourceNotFoundException;
import com.codeWithMadhuri.blog.payloads.CategoryDto;
import com.codeWithMadhuri.blog.repositories.CategoryRepository;
import com.codeWithMadhuri.blog.services.CategoryServiceI;

@Service
public class CategoryServiceImpl implements CategoryServiceI {
	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	// 1 createCategory
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		logger.info("createCategory from CategoryServiceImpl  method started.");
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category createdCat = this.categoryRepository.save(cat);
		logger.info("createCategory from CategoryServiceImpl  method ended.");
		return this.modelMapper.map(createdCat, CategoryDto.class);
	}

	// 2 updateCategory
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		logger.info("updateCategory from CategoryServiceImpl  method started.");
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(cat);
		logger.info("updateCategory from CategoryServiceImpl  method ended.");
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

//3 deleteCategory
	@Override
	public void deleteCategory(Integer categoryId) {
		logger.info("deleteCategory from CategoryServiceImpl  method started.");
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepository.delete(cat);
		logger.info("deleteCategory from CategoryServiceImpl  method ended.");
	}

//4 getSingleCategory
	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {
		logger.info("getSingleCategory from CategoryServiceImpl  method started.");
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		logger.info("getSingleCategory from CategoryServiceImpl  method ended.");
		return this.modelMapper.map(cat, CategoryDto.class);
	}

//5 getAllCategories
	@Override
	public List<CategoryDto> getAllCategories() {
		logger.info("getAllCategories from CategoryServiceImpl  method started.");
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> categoriesList = categories.stream()
				.map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		logger.info("getAllCategories from CategoryServiceImpl  method ended.");
		return categoriesList;
	}

}
