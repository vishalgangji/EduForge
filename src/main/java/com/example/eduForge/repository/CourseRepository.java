package com.example.eduForge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduForge.entity.CourseEntity;

public interface CourseRepository  extends JpaRepository<CourseEntity,Long>{

}
