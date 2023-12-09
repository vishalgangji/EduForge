package com.example.eduForge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduForge.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

}
