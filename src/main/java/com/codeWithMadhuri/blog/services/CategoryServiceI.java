package com.codeWithMadhuri.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.payloads.CategoryDto;


public interface CategoryServiceI {
	
	//1 create
	public CategoryDto createCategory(CategoryDto categoryDto);
	//2 update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//3 delete
	public void deleteCategory(Integer categoryId);
	//4 getSingle
	public CategoryDto getSingleCategory(Integer categoryId);
	//5 getAll
	public  List<CategoryDto> getAllCategories();
	

}
