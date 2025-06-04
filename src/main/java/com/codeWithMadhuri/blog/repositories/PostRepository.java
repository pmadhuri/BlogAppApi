package com.codeWithMadhuri.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post>  findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	//practice purpose only
	//due to hibernate version issue if facing issue with number"InvalidDataAccessApiUsageException"
	//and  How dynamic value take??
	//Query("select p from Post p where p.title like :key")
	//List<Post> findByTitleContaining(@Param("key") String title);
	//in PostServiceImpl use  ("%"+keyword+"%")

}
