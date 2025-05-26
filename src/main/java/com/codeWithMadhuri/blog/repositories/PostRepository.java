package com.codeWithMadhuri.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post>  findByCategory(Category category);

}
