package com.example.eduForge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.eduForge.entity.UserProfileEntity;
import com.example.eduForge.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
public List<UserProfileEntity>getUsers()
{
	return userRepository.findAll();
}
public UserProfileEntity createUser(UserProfileEntity userProfileEntity)
{
//	user.setUserId(UUID.randomUUID().toString());
	userProfileEntity.setPassword(passwordEncoder.encode(userProfileEntity.getPassword()));
	System.out.println("hi s");
	return userRepository.save(userProfileEntity);
}
}
