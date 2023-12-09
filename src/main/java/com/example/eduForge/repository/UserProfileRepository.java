package com.example.eduForge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eduForge.entity.UserProfileEntity;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity,Long> {

	UserProfileEntity findByEmail(String email);
//	UserProfileEntity findByContact(String contact);
//	UserProfileEntity findByUniqueId(String unique);
//	List<UserProfileEntity> findAllByUniqueId(String unique);
	List<UserProfileEntity> findAllByEmail(String email);
//	List<UserProfileEntity> findAllByContact(String contact);
	UserProfileEntity findAllById(Long id);
//	UserProfileEntity findByEmailAndUniqueId(String email, String uniqueId);
//	UserProfileEntity findByContactAndUniqueId(String contact, String uniqueId);
	UserProfileEntity findUniqueIdByEmail(String email);
//	List<UserProfileEntity> findAllById(String string);

}
