package com.codeWithMadhuri.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.controllers.UserController;
import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.entities.User;
import com.codeWithMadhuri.blog.exceptions.ResourceNotFoundException;
import com.codeWithMadhuri.blog.payloads.PostDto;
import com.codeWithMadhuri.blog.payloads.PostResponse;
import com.codeWithMadhuri.blog.repositories.CategoryRepository;
import com.codeWithMadhuri.blog.repositories.PostRepository;
import com.codeWithMadhuri.blog.repositories.UserRepository;
import com.codeWithMadhuri.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	private static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	// 1 createPost
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		logger.info("createPost from PostServiceImpl  method started.");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		;
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepository.save(post);

		logger.info("createPost from PostServiceImpl  method ended.");
		return this.modelmapper.map(newPost, PostDto.class);
	}

	// 2 updatePost
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		logger.info("updatePost from PostServiceImpl  method started.");
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepository.save(post);
		logger.info("updatePost from PostServiceImpl  method ended.");
		return this.modelmapper.map(updatedPost, PostDto.class);
	}

	// 3 deletePost
	@Override
	public void deletePost(Integer postId) {
		logger.info("deletePost from PostServiceImpl  method started.");
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
		logger.info("deletePost from PostServiceImpl  method ended.");
		this.postRepository.delete(post);

	}

	// 4 getAllPost
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		//pagination
		logger.info("Pagination started");
//		int pageSize=5;
//		int pageNumber=1;
		
	/*	Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}*/
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber,pageSize,sort);
		
		logger.info("getAllPost from PostServiceImpl  method started.");
		//List<Post> allposts = this.postRepository.findAll();
		//pagination
		
		Page<Post> pagePost = this.postRepository.findAll(p);
		List<Post> allposts = pagePost.getContent();
		
		List<PostDto> postDtos = allposts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("getAllPost from PostServiceImpl  method ended.");
		//Pagination
		logger.info("Pagination logic here.");
		PostResponse postResponse =new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	// 5 getPostById
	@Override
	public PostDto getPostById(Integer postId) {
		logger.info("getPostById from PostServiceImpl  method started.");
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		logger.info("getPostById from PostServiceImpl  method ended.");
		return this.modelmapper.map(post, PostDto.class);

	}

	// 6 getPostByCategory
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		logger.info("getPostByCategory from PostServiceImpl  method started.");
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepository.findByCategory(cat);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("getPostByCategory from PostServiceImpl  method ended.");
		return postDto;
	}

	// 7 getPostByUser
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		logger.info("getPostByUser from PostServiceImpl  method started.");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("getPostByUser from PostServiceImpl  method ended.");
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);//("%"+keyword+"%")
		List<PostDto> postDtos = posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
