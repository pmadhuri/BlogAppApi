package com.codeWithMadhuri.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {

	
	private Integer categoryId;
	@NotEmpty
	@Size(min = 4,message = "min size of category is 4")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10,message = "min size of categoryDescription is 10")
	private String categoryDescription;
	
}
