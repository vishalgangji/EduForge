package com.example.eduForge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class UserEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String companyName;
	private String  email;
	private String contact;
	private String password;
	private String state;
	private String uniqueId;

	
	
	

}
