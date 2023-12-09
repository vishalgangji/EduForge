package com.example.eduForge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduForge.entity.UserProfileEntity;


public interface UserRepository extends JpaRepository<UserProfileEntity,Long>{
public Optional<UserProfileEntity>findByEmail(String email);
}
