package com.codeWithMadhuri.blog.controllers;

import java.util.List;

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

	@Autowired
	private CategoryServiceI categoryServiceI;

	// create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createCategory = this.categoryServiceI.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}
	
	//update
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		
		CategoryDto updateCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(updateCategory ,HttpStatus.OK);
		
	}
	
	//delete
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cId){
		this.categoryServiceI.deleteCategory(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully..",true),HttpStatus.OK);
		
		
	}
	
	//get
	
	@GetMapping("/{categoryId}")
public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		
		CategoryDto singleCategoryDto = this.categoryServiceI.getSingleCategory(categoryId);
		return new ResponseEntity<CategoryDto>(singleCategoryDto,HttpStatus.OK);
		
	}
	
	//getAll
	
	@GetMapping("/")
public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> allCategoryDto = this.categoryServiceI.getAllCategories();
		return ResponseEntity.ok(allCategoryDto);
		
	}	
	
	
}
