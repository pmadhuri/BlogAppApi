package com.codeWithMadhuri.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "username must be min of 4 characters...")
	private String name;
	@NotBlank(message = "Email id is not valid")
	  @Email(message = "Invalid email format")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 10 ,message = "Password must be min of 3 chars and max 10 chars")
	private String password;
	@NotEmpty(message = "About feild empty")
	private String about;
}
