package com.example.eduForge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.eduForge.entity.UserProfileEntity;
import com.example.eduForge.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		UserProfileEntity userProfileEntity=userRepository.findByEmail(phone).orElseThrow(()->new RuntimeException("user not not"));
		return userProfileEntity;
	}

}
