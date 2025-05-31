package com.codeWithMadhuri.blog.payloads;

import java.util.Date;

import com.codeWithMadhuri.blog.entities.Category;
import com.codeWithMadhuri.blog.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
 
	private Integer postId;
	@NotEmpty
	@Size(min = 4,max = 15,  message = "min size of title is 4")
	private String title;
	@NotEmpty
	@Size(min = 4,max = 15,  message = "min size of title is 4")
	private String content;
	@NotEmpty
	private String imageName;
	@NotEmpty
	private Date addedDate;
	@NotEmpty
	private CategoryDto category;
	@NotEmpty
	private UserDto user;
	
}
