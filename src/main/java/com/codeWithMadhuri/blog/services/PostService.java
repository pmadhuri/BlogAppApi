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
	void deletePost(Integer postId);
	
	//getAllPosts
	List<PostDto> getAllPost();
	
	//getSinglePost
	
	PostDto getPostById(Integer postId);
	
	//ListOf posts by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//ListOf posts by User
		List<PostDto> getPostByUser(Integer userId);
	//Post search by keyword
	
}
