package com.example.eduForge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduForge.dtos.StudentDto;
import com.example.eduForge.entity.CourseEntity;
import com.example.eduForge.entity.StudentEntity;
import com.example.eduForge.entity.StudentEntity;
import com.example.eduForge.repository.CourseRepository;
import com.example.eduForge.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public StudentEntity insertStudent(StudentDto studentDto) {
		if (studentDto.getStudentName() == null || studentDto.getStudentName() == "")
			throw new IllegalArgumentException("please provide Student name");
		StudentEntity studentEntity = new StudentEntity();
		if (studentDto.getStudentName() != null) {
			studentEntity.setStudentName(studentDto.getStudentName());
		}
		if (studentDto.getStudentEmail() != null) {
			studentEntity.setStudentEmail(studentDto.getStudentEmail());
		}
		if (studentDto.getAddress() != null) {
			studentEntity.setAddress(studentDto.getAddress());
		}
		if (studentDto.getPhoneNumber() != null) {
			studentEntity.setPhoneNumber(studentDto.getPhoneNumber());
		}
		if (studentDto.getCourseId() == null) {
			throw new IllegalArgumentException("plase provide course id ");
		}
		Optional<CourseEntity> courseEnti = courseRepository.findById(studentDto.getCourseId());
		if (courseEnti == null) {
			throw new IllegalArgumentException("no course found ");

		}
		studentEntity.setCourse(studentDto.getCourseId());
		return studentRepository.save(studentEntity);
	}

	@Override
	public boolean deleteStudent(Long id) {
		Optional<StudentEntity> courseEnti = studentRepository.findById(id);
		if (courseEnti == null) {
			throw new IllegalArgumentException("no student found");

		}
		studentRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean updateStudent(StudentDto studentDto) {
		Optional<StudentEntity> courseEnti = studentRepository.findById(studentDto.getId());
		if (courseEnti == null) {
			throw new IllegalArgumentException("no student found");

		}
		StudentEntity studentEntity = courseEnti.get();
		if (studentDto.getStudentName() != null) {
			studentEntity.setStudentName(studentDto.getStudentName());
		}
		if (studentDto.getStudentEmail() != null) {
			studentEntity.setStudentEmail(studentDto.getStudentEmail());
		}
		if (studentDto.getAddress() != null) {
			studentEntity.setAddress(studentDto.getAddress());
		}
		if (studentDto.getPhoneNumber() != null) {
			studentEntity.setPhoneNumber(studentDto.getPhoneNumber());
		}
		if (studentDto.getCourseId() == null) {
			throw new IllegalArgumentException("plase provide course id ");
		}
		Optional<CourseEntity> courseEnt = courseRepository.findById(studentDto.getCourseId());
		if (courseEnt == null) {
			throw new IllegalArgumentException("no course found ");

		}
		studentEntity.setCourse(studentDto.getCourseId());
		studentRepository.save(studentEntity);
		return true;
	}

	@Override
	public boolean deleteInvoice(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StudentEntity> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public StudentEntity getStudent(Long id) {
		Optional<StudentEntity> courseEnti = studentRepository.findById(id);
		if (courseEnti == null) {
			throw new IllegalArgumentException("no student found");

		}
		return courseEnti.get();
	}
}
