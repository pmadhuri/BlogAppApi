package com.codeWithMadhuri.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeWithMadhuri.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	

}
