package com.codeWithMadhuri.blog.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.codeWithMadhuri.blog.payloads.ApiResponse;
import com.codeWithMadhuri.blog.payloads.CategoryDto;
import com.codeWithMadhuri.blog.services.CategoryServiceI;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryServiceI categoryServiceI;

	//1 createCategory
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		logger.info("createCategory method started");
		CategoryDto createCategory = this.categoryServiceI.createCategory(categoryDto);
		logger.info("createCategory method ended");
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}
	
	//2 updateCategory
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		logger.info("updateCategory method started");
		CategoryDto updateCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);
		logger.info("updateCategory method ended");
		return new ResponseEntity<CategoryDto>(updateCategory ,HttpStatus.OK);
		
	}
	
	//3 deleteCategory
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cId){
		logger.info("deleteCategory method started");
		this.categoryServiceI.deleteCategory(cId);
		logger.info("deleteCategory method ended");
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully..",true),HttpStatus.OK);
		
		
	}
	
	//4 getSingleCategory
	
	@GetMapping("/{categoryId}")
public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		logger.info("getSingleCategory method started");
		CategoryDto singleCategoryDto = this.categoryServiceI.getSingleCategory(categoryId);
		logger.info("getSingleCategory method ended");
		return new ResponseEntity<CategoryDto>(singleCategoryDto,HttpStatus.OK);
		
	}
	
	//5 getAllCategory
	
	@GetMapping("/")
public ResponseEntity<List<CategoryDto>> getAllCategory(){
		logger.info("getAllCategory method started");
		List<CategoryDto> allCategoryDto = this.categoryServiceI.getAllCategories();
		logger.info("getAllCategory method ended");
		return ResponseEntity.ok(allCategoryDto);
		
	}	
	
	
}
