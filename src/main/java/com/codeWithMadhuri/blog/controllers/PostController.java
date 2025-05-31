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

import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.payloads.ApiResponse;
import com.codeWithMadhuri.blog.payloads.PostDto;
import com.codeWithMadhuri.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	private static Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	private PostService postService;

	// 1 createPost
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		logger.info("createPost method started");
		PostDto createPost = this.postService.createPost(postDto, categoryId, userId);
		logger.info("createPost method ended");
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// 2 getPostByUser
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		logger.info("getPostByUser method started");
		List<PostDto> posts = this.postService.getPostByUser(userId);
		logger.info("getPostByUser method ended");
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// 3 getPostsByCategory

	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		logger.info("getPostsByCategory method started");
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		logger.info("getPostsByCategory method ended");
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	// 4 getAllPost
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost() {
		logger.info("getAllPost method started");
		List<PostDto> allPost = this.postService.getAllPost();
		logger.info("getAllPost method ended");
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}

	// 5 getPostById

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		logger.info("getPostById method started");
		PostDto postDto = this.postService.getPostById(postId);
		logger.info("getPostById method ended");
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	// 6 deletePost
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		logger.info("deletePost method started");
		this.postService.deletePost(postId);
		logger.info("deletePost method ended");
		return new ApiResponse("Post is deleted successfully....", true);

	}

	// 7 updatePost
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		logger.info("updatePost method started");
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("updatePost method ended");
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

}
