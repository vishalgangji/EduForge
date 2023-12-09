package com.example.eduForge.service;

import java.util.List;

import com.example.eduForge.dtos.StudentDto;
import com.example.eduForge.entity.StudentEntity;

public interface StudentService {

	StudentEntity insertStudent(StudentDto studentDto);

	boolean deleteStudent(Long id);

	boolean updateStudent(StudentDto studentDto);

	boolean deleteInvoice(Long id);

	List<StudentEntity> getAllStudent();

	StudentEntity getStudent(Long id);

}
