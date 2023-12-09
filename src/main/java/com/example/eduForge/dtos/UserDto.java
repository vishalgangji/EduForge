package com.example.eduForge.dtos;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String companyName;
	private String  email;
	private String contact;
	private String password;
	private String state;
	private String uniqueId;
	
}
