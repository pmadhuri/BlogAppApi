package com.codeWithMadhuri.blog.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeWithMadhuri.blog.entities.Comment;
import com.codeWithMadhuri.blog.entities.Post;
import com.codeWithMadhuri.blog.exceptions.ResourceNotFoundException;
import com.codeWithMadhuri.blog.payloads.CommentDto;
import com.codeWithMadhuri.blog.repositories.CommentRepository;
import com.codeWithMadhuri.blog.repositories.PostRepository;
import com.codeWithMadhuri.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository ;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment updatedComment = this.commentRepository.save(comment);
		 
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	Comment comment	 = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepository.save(comment);
	}

}
