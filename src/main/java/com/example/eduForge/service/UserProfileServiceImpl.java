package com.example.eduForge.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.eduForge.dtos.UserProfileDto;
import com.example.eduForge.entity.UserProfileEntity;
import com.example.eduForge.repository.UserProfileRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileRepository userProfileRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserProfileEntity CreateUserProfile(UserProfileDto userProfileDto, HttpSession session) {

		UserProfileEntity isEx = userProfileRepository.findByEmail(userProfileDto.getEmail());

		if (isEx != null) {
			throw new IllegalArgumentException("Email Already Exist ");

		}

		UserProfileEntity userProfile = new UserProfileEntity();

		if (userProfileDto.getPassword() != null) {
			userProfile.setPassword(userProfileDto.getPassword());
		}
		if (userProfileDto.getEmail() != null) {
			userProfile.setEmail(userProfileDto.getEmail());
		}
		if (userProfileDto.getName() != null) {
			userProfile.setName(userProfileDto.getName());
		}
		if (userProfileDto.getRole() != null) {
			userProfile.setRole(userProfileDto.getRole());
		}

		return userProfileRepository.save(userProfile);

	}

	

	@Override
	public UserProfileEntity signUp(UserProfileDto userProfileDto) {
		List<UserProfileEntity> email = userProfileRepository.findAllByEmail(userProfileDto.getEmail());

		if (userProfileDto.getEmail() == null) {
			throw new IllegalArgumentException("Email Is Mandatory");
		}

		if (userProfileDto.getRole() == null) {
			throw new IllegalArgumentException("Contact Number Is Mandatory");
		}

		if (userProfileDto.getPassword() == null) {
			throw new IllegalArgumentException("Password Is Mandatory");
		}

		if (userProfileDto.getName() == null) {
			throw new IllegalArgumentException("State Is Mandatory");
		}

		if (email.size() > 0) {
			throw new IllegalArgumentException("Email Already Exist  ");
		}

		UserProfileEntity user = new UserProfileEntity();
		user.setName(userProfileDto.getName());
		user.setEmail(userProfileDto.getEmail());
		user.setRole(userProfileDto.getRole());
		user.setPassword(passwordEncoder.encode(userProfileDto.getPassword()));
		;

		return userProfileRepository.save(user);
	}

	@Override
	public UserProfileEntity login(UserProfileDto userProfileDto) {
		UserProfileEntity email = userProfileRepository.findByEmail(userProfileDto.getEmail());
		if (email != null) {
			if (email.getPassword().equals(userProfileDto.getPassword())) {
				return email;
			} else {
				throw new IllegalArgumentException("Invalid password ");
			}
		} else {
			throw new IllegalArgumentException("Invalid Email ");
		}
	}

	@Override
	public UserProfileEntity getUniqueId(String email) {
		UserProfileEntity userprofileEntity = userProfileRepository.findUniqueIdByEmail(email);
		return userprofileEntity;

	}

}