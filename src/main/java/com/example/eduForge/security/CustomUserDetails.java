package com.example.eduForge.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
	String getUniqueId();
}
