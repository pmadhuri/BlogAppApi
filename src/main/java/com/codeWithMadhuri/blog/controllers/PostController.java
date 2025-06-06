package com.codeWithMadhuri.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeWithMadhuri.blog.config.AppConstants;
import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.payloads.ApiResponse;
import com.codeWithMadhuri.blog.payloads.PostDto;
import com.codeWithMadhuri.blog.payloads.PostResponse;
import com.codeWithMadhuri.blog.services.FileServices;
import com.codeWithMadhuri.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	private static Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	private PostService postService;
	@Autowired
	private FileServices fileServices;
	// path
	@Value("${project.image}")
	private String path;

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

	// 4 getAllPost // url for postman
	// //posts?pageNumber=1&pageSize=2&sortBy=postId&sortDir=dsc
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		logger.info("getAllPost method started");
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		logger.info("getAllPost method ended");
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
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

	// 7 search

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {

		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}

	// 8 post image upload
	@PostMapping("/post/image/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam ("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileServices.uploadImage(path, image);
		
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	//9 method to serv file
	@GetMapping(value =  "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadFile(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		
		InputStream resource=this.fileServices.getResources(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}

}
