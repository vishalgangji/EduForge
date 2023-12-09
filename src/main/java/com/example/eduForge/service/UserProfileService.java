package com.example.eduForge.service;

import java.util.List;

import com.example.eduForge.dtos.UserProfileDto;
import com.example.eduForge.entity.UserProfileEntity;

import jakarta.servlet.http.HttpSession;

public interface UserProfileService {

	UserProfileEntity CreateUserProfile(UserProfileDto userProfileDto, HttpSession session);

	UserProfileEntity signUp(UserProfileDto userProfileDto);

	UserProfileEntity login(UserProfileDto userProfileDto);

	UserProfileEntity getUniqueId(String email);

}
