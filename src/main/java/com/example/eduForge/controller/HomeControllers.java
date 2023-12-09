package com.example.eduForge.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.eduForge.service.UserServices;

@RestController
@RequestMapping("/home")
public class HomeControllers {
	@Autowired
	UserServices userServices;

	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}

	@GetMapping("/hello")
	public String hello() {

		return "false";
	}
}
