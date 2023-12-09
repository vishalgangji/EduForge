package com.example.eduForge.dtos;

import lombok.Data;

@Data
public class StudentDto {
	private Long id;
	private String studentName;
	private String studentEmail;
	private String address;
	private String phoneNumber;
	private Long courseId;
}
