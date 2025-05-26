package com.codeWithMadhuri.blog.services;

import java.util.List;

import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.payloads.PostDto;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer PostId);
	
	
	//delete
	void deletePost();
	
	//getAllPosts
	List<Post> getAllPost();
	
	//getSinglePost
	
	PostDto getPostById(Integer postId);
	
	//ListOf posts by category
	List<Post> getPostByCategory(Integer categoryId);
	
	//ListOf posts by User
		List<Post> getPostByUser(Integer userId);
	//Post search by keyword
	
}
