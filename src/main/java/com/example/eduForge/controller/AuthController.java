package com.example.eduForge.controller;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eduForge.entity.UserProfileEntity;
import com.example.eduForge.models.JwtRequest;
import com.example.eduForge.models.JwtResponse;
import com.example.eduForge.dtos.UserProfileDto;
import com.example.eduForge.security.CustomUserDetails;
import com.example.eduForge.security.JwtHelper;
import com.example.eduForge.service.UserProfileService;
import com.example.eduForge.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	UserProfileService userProfileService;
	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserServices userServices;
	@Autowired
	ObjectMapper mapper;

	@PostMapping("/logout")

	public ObjectNode logout(HttpServletResponse res) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			Cookie cookieToDelete = new Cookie("jwtToken", null); // Replace with your cookie name
			cookieToDelete.setMaxAge(0); // Set the cookie's max age to 0 to delete it
			cookieToDelete.setPath("/auth"); // Set the cookie's path to match the original path
			res.addCookie(cookieToDelete);
			objectNode.put("responseMessage", "cookie Invalidate successfully");
			return (objectNode);

		} catch (Exception e) {
			// Handle the exception here, you can log it or perform other error handling
			// tasks
			objectNode.put("error", "An error occurred while invalidating the cookie");
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set a 500 Internal Server Error status code
			return objectNode;
		}

	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request, HttpServletResponse res) {
		try {
			if (request.getEmail() == null || request.getPassword() == null) {
				throw new IllegalArgumentException("Email And Password is Mandatory");
			}
			System.out.println(request.getEmail() + request.getPassword());
			this.doAuthenticate(request.getEmail(), request.getPassword());

			CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
					.loadUserByUsername(request.getEmail());
			String token = this.helper.generateToken(userDetails);

			Cookie cookieJwt = new Cookie("jwtToken", token); // Replace with your cookie name
			cookieJwt.setMaxAge(500); // Set the cookie's max age to 0 to delete it
			cookieJwt.setPath("/auth"); // Set the cookie's path to match the original path
			res.addCookie(cookieJwt);

//	       String uniqueId=userProfileService.getUniqueId(request.getEmail());
			UserProfileEntity userProfileEntity = userProfileService.getUniqueId(request.getEmail());

			JwtResponse response = JwtResponse.builder().jwtToken(token)
//	                .uniqueId(((UserProfileDto) userDetails).getUniqueId())
					.uniqueId(userProfileEntity.getUniqueId()).message("Login Successful").build();

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IllegalArgumentException e) {

			JwtResponse response = JwtResponse.builder().message("Invalid argument: " + e.getMessage()).build();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JwtResponse response = JwtResponse.builder().message(e.getMessage()).build();

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void doAuthenticate(String phone, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phone, password);
		try {
			manager.authenticate(authentication);
			System.out.println(password);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/signUp")
	public ResponseEntity<ObjectNode> createUser(@RequestBody UserProfileDto userProfileDto) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			UserProfileEntity user = userProfileService.signUp(userProfileDto);
			String id = user.getUniqueId();

			objectNode.put("responseCode", "200");
			objectNode.put("status", "success");
			objectNode.put("responseMessage", id);

			return ResponseEntity.ok().body(objectNode);
		} catch (IllegalArgumentException e) {
			objectNode.put("responseCode", "400");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", e.getMessage());
			return ResponseEntity.badRequest().body(objectNode);
		}
//	    	 return userServices.createUser(userProfileDto);
	}

//	    @PostMapping("/create-user")
//	    public EMISaverEntity createUser(@RequestBody EMISaverEntity eMISaverEntity)
//	    {
//	    	return userServices.createUser(eMISaverEntity);
//	    }
	@GetMapping("/get-jwt-cookie")
	public String getJwtCookie(@CookieValue("jwtToken") String jwtToken) {
		return helper.getUsernameFromToken(jwtToken);

	}

}
