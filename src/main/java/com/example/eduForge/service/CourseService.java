package com.example.eduForge.service;

import java.util.List;

import com.example.eduForge.dtos.CourseDto;
import com.example.eduForge.entity.CourseEntity;

//import com.example.eduForge.dtos.ContactPersonVendorDto;
//import com.example.eduForge.dtos.VendorDto;
//import com.example.eduForge.entity.ContactPersonVendorEntity;
//import com.example.eduForge.entity.CustomerEntity;
//import com.example.eduForge.entity.VendorEntity;

import jakarta.servlet.http.HttpSession;

public interface CourseService {
	CourseEntity insertCourse(CourseDto courseDto);

	boolean updateCourse(CourseDto courseDto);

	boolean deleteInvoice(Long id);

	List<CourseEntity> getAllCourse();
}


