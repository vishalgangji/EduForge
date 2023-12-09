package com.example.eduForge.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eduForge.dtos.CourseDto;
import com.example.eduForge.entity.CourseEntity;
import com.example.eduForge.repository.CourseRepository;



@Service
public class CourseServiceImpl implements CourseService {
@Autowired
CourseRepository courseRepository;
	@Override
	public CourseEntity insertCourse(CourseDto courseDto) {
		// TODO Auto-generated method stub
		
		if(courseDto.getCourseName()==null||courseDto.getCourseName()=="")
			throw new IllegalArgumentException("please provide course name");
		CourseEntity courseEntity=new CourseEntity();
		if (courseDto.getCourseName() != null) {
			courseEntity.setCourseName(courseDto.getCourseName());
		}
		if (courseDto.getCourseDuration() != null) {
			courseEntity.setCourseDuration(courseDto.getCourseDuration());
		}
		if (courseDto.getDepartment() != null) {
			courseEntity.setDepartment(courseDto.getDepartment());
		}
	
		 return  courseRepository.save(courseEntity);
		
	}

	@Override
	public boolean updateCourse(CourseDto courseDto) {
		if(courseDto.getId()==null||courseDto.getId()<=0)
			throw new IllegalArgumentException("please provide course id");
		
		Optional<CourseEntity> courseEnti=courseRepository.findById(courseDto.getId());
		if(courseEnti==null)
		{
			throw new IllegalArgumentException("no course found ");
			
		}
		CourseEntity courseEntity=courseEnti.get();
		
		if (courseDto.getCourseName() != null) {
			courseEntity.setCourseName(courseDto.getCourseName());
		}
		if (courseDto.getCourseDuration() != null) {
			courseEntity.setCourseDuration(courseDto.getCourseDuration());
		}
		if (courseDto.getDepartment() != null) {
			courseEntity.setDepartment(courseDto.getDepartment());
		}
	
		  courseRepository.save(courseEntity);
		  
		return true;
	}

	@Override
	public boolean deleteInvoice(Long id) {

		Optional<CourseEntity> courseEnti=courseRepository.findById(id);
		if(courseEnti==null)
		{
			throw new IllegalArgumentException("no course found ");
			
		}
		courseRepository.deleteById(id);
		return true;
	}

	@Override
	public List<CourseEntity> getAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

}
