package com.example.eduForge.dtos;

import lombok.Data;

@Data
public class UserProfileDto {

	private Long id;
	private String name;
	private String role;
	private String password;
	private String email;

}
