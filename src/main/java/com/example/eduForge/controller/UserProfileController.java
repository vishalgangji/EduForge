//package com.example.eduForge.controller;
//
//import java.io.File;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.eduForge.config.CorsConfig;
//import com.example.eduForge.dtos.UserProfileDto;
//import com.example.eduForge.entity.BillsEntity;
//import com.example.eduForge.entity.GstSettingEntity;
//import com.example.eduForge.entity.UserProfileEntity;
//import com.example.eduForge.repository.GstSettingRepository;
//import com.example.eduForge.repository.UserProfileRepository;
//import com.example.eduForge.service.UserProfileService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//@RequestMapping("/api")
//@RestController
//public class UserProfileController {
//	
//	@Autowired
//	UserProfileService userProfileService;
//	@Autowired
//	UserProfileRepository userProfileRepository;
//	@Autowired
//	ObjectMapper mapper;
//
//	@PostMapping("user/signUp")
//    @CrossOrigin(origins = CorsConfig.reactUrl) // Specify the allowed origin (your React app)
//	
//	public ResponseEntity<ObjectNode> signUp(@RequestBody UserProfileDto userProfileDto) {
//		ObjectNode objectNode = mapper.createObjectNode();
//		try 
//		{
//			UserProfileEntity user = userProfileService.signUp(userProfileDto);
//			String id=user.getUniqueId();
//			GstSettingEntity gstSettingEntity=new GstSettingEntity();
//			gstSettingEntity.setUniqueId(id);
//			objectNode.put("responseCode", "200");
//			objectNode.put("status", "success");
//			objectNode.put("responseMessage", id);
//		
//			return ResponseEntity.ok().body(objectNode);
//		} 
//		catch (IllegalArgumentException e) {
//			objectNode.put("responseCode", "400");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", e.getMessage());
//			return ResponseEntity.badRequest().body(objectNode);
//		}
//	}
//	
//	@PostMapping("user/login")
//	public ResponseEntity<ObjectNode> login(@RequestBody UserProfileDto userProfileDto, HttpSession session) {
//	    ObjectNode objectNode = mapper.createObjectNode();
//	    try {
//	        UserProfileEntity user = userProfileService.login(userProfileDto);
//	         String uniqueID=user.getUniqueId();
//	         String setup=user.getSetup();
//	        // Set the uniqueId in the response message
//	        objectNode.put("responseCode", "200");
//	        objectNode.put("status", "success");
//	        objectNode.put("responseMessage", uniqueID); // Set uniqueId in responseMessage
//	        objectNode.put("responseMessage1", setup);
//	        // Store the uniqueId in the session
//	     //   session.setAttribute("uniqueId",  uniqueID);
//	    //    System.out.println(session.getAttribute("uniqueId"));
//
//	        return ResponseEntity.ok().body(objectNode);
//	    } catch (IllegalArgumentException e) {
//	        objectNode.put("responseCode", "400");
//	        objectNode.put("status", "error");
//	        objectNode.put("responseMessage", e.getMessage());
//	        return ResponseEntity.badRequest().body(objectNode);
//	    }
//	}
//	@PostMapping("/userLogout")
//	public ObjectNode userLogout(HttpServletRequest request) {
//		
//		request.getSession().invalidate();
//		ObjectNode objectNode = mapper.createObjectNode();
//		objectNode.put("responseCode", "200");
//        objectNode.put("responseMessage", "User Logout Success !");
//		return  objectNode;
//
//	}
//
//    @PostMapping("/userprofile/Create")
//    public ResponseEntity<ObjectNode> createUserProfile( @RequestBody UserProfileDto userProfileDto, HttpSession session) {
//        ObjectNode objectNode = mapper.createObjectNode();
//     
//        try
//        {
//        	
//        	
//            UserProfileEntity user = userProfileService.CreateUserProfile(userProfileDto, session);
//
//            if (user != null) {
//                objectNode.put("responseCode", "200");
//                objectNode.put("responseMessage", "Profile Created Successfully");
//                objectNode.put("responseMessage", user.getId());
//                
//                // Add other response data as needed
//            } else {
//                objectNode.put("responseCode", "400");
//                objectNode.put("responseMessage", "Failed to Create Profile");
//                // Add other response data as needed
//            }
//        }
//        catch(IllegalArgumentException e)
//        {
//        	 objectNode.put("responseCode", "400");
//             objectNode.put("responseMessage", "Failed to Create Profile"+" "+e.getMessage());
//        }
//        
//        return ResponseEntity.ok(objectNode);
//    }
//    
//    
//    @PutMapping("/orgnization/Create")
//    public ResponseEntity<ObjectNode> orgnizationCreate( @RequestBody UserProfileDto userProfileDto, HttpSession session) {
//        ObjectNode objectNode = mapper.createObjectNode();
//
//        try
//        {
//            UserProfileEntity user = userProfileService.orgnizationCreate(userProfileDto, session);
//
//            if (user != null) {
//            	user.setSetup("1");
//                objectNode.put("responseCode", "200");
//                objectNode.put("responseMessage", "Organization Created Successfully");
//                // Add other response data as needed
//            } else {
//                objectNode.put("responseCode", "400");
//                objectNode.put("responseMessage", "Failed to Create Organization");
//                // Add other response data as needed
//            }
//        }
//        catch(IllegalArgumentException e)
//        {
//        	 objectNode.put("responseCode", "400");
//             objectNode.put("responseMessage", "Failed to Create Profile"+" "+e.getMessage());
//        }
//        
//        return ResponseEntity.ok(objectNode);
//    }
//    
//    @PostMapping("/fetchByUniqueId")
//    public ResponseEntity<ObjectNode> getAllEntities( HttpSession session) {
//        ObjectNode objectNode = mapper.createObjectNode();
//
//        List<UserProfileEntity> entities = userProfileService.fetchByUniqueId(session);
//        if (entities != null) {
//            objectNode.put("responseCode", "200");
//            objectNode.putPOJO("responseMessage", entities);
//            return ResponseEntity.ok().body(objectNode);
//            // Add other response data as needed
//        } else {
//            objectNode.put("responseCode", "400");
//            objectNode.put("responseMessage", "Failed to Fetch Organization");
//            // Add other response data as needed
//        }
//        return ResponseEntity.badRequest().body(objectNode);
//    }
//    
//    @PostMapping("/logoUpload/{id}")
//	 public ResponseEntity<?> uploadImage(@RequestParam("logo") MultipartFile file,@PathVariable Long id, HttpSession session, HttpServletRequest request) {
//	     ObjectNode objectNode = mapper.createObjectNode();
//
//	     if (request.getAttribute("uploading") != null) {
//	            objectNode.put("responseCode", "400");
//	            objectNode.put("status", "error");
//	            objectNode.put("responseMessage", "Another upload is already in progress.");
//	            return ResponseEntity.badRequest().body(objectNode);
//	        }
//
//	        // Set the 'uploading' attribute to prevent concurrent uploads
//	        request.setAttribute("uploading", true);
//	     try {
//	         if (file.isEmpty()) {
//	             objectNode.put("responseCode", "400");
//	             objectNode.put("status", "error");
//	             objectNode.put("responseMessage", "No file selected for upload.");
//	             return ResponseEntity.badRequest().body(objectNode);
//	         }
//
//	         // Generate a unique file name (timestamp + random number)
//	         String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
//	         byte[] fileData = file.getBytes();
//	         String folderPath = "C:\\Users\\ANONYMOUS\\Downloads\\zerosErp 5-10-23\\zerosErp\\target\\uploads";
//	         String savePath = folderPath + File.separator + uniqueFileName;
//
//	         Files.write(Paths.get(savePath), fileData);
//
//	         // Save the unique file name to the database
//	         userProfileService.saveFileNameToDatabase(uniqueFileName, id);
//
//	         objectNode.put("responseCode", "200");
//	         objectNode.put("status", "success");
//	         objectNode.put("responseMessage", "Image uploaded successfully!");
//
//	         return ResponseEntity.ok().body(objectNode);
//	     } catch (IOException e) {
//	         objectNode.put("responseCode", "400");
//	         objectNode.put("status", "error");
//	         objectNode.put("responseMessage", "Error uploading image." + e.getMessage());
//	         return ResponseEntity.badRequest().body(objectNode);
//	     }
//	 }
//
//	 // Generate a unique file name using timestamp and a random number
//	 private String generateUniqueFileName(String originalFileName) {
//	     long timestamp = System.currentTimeMillis();
//	     int randomNumber = (int) (Math.random() * 10000);
//	     String fileExtension = getFileExtension(originalFileName);
//
//	     // Combine timestamp, random number, and file extension to create a unique name
//	     return timestamp + "_" + randomNumber + "." + fileExtension;
//	 }
//
//	 // Extract file extension from the original file name
//	 private String getFileExtension(String fileName) {
//	     int dotIndex = fileName.lastIndexOf(".");
//	     if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
//	         return fileName.substring(dotIndex + 1);
//	     }
//	     return "";
//	 }
//
//	 @PostMapping("/fetchUserProfile")
//		public ResponseEntity<ObjectNode> fetchUserProfile(@RequestBody Map<String, String> requestBody,
//		        HttpSession session) {
//		    ObjectNode objectNode = mapper.createObjectNode();
//
//		    try {
//		         String uniqueId = requestBody.get("uniqueId");
//		      //  String uniqueId = "SITD9893";
//		        if (uniqueId == null) {
//		            objectNode.put("responseCode", "400");
//		            objectNode.put("status", "error");
//		            objectNode.put("responseMessage", "You Are Not Validated");
//		            return ResponseEntity.badRequest().body(objectNode);
//		        }
//		        System.out.println("second");
//		        // Use the "uniqueId" value to fetch invoices
//		        List<UserProfileEntity> userProfile = userProfileService.fetchUserProfile(uniqueId);
//		        System.out.println("hii" + userProfile);
//		        if (!userProfile.isEmpty()) {
//		            objectNode.put("responseCode", "200");
//		            objectNode.put("status", "success");
//		            objectNode.putPOJO("responseMessage", userProfile);
//		            return ResponseEntity.ok(objectNode);
//		        } else {
//		            objectNode.put("responseCode", "400");
//		            objectNode.put("status", "error");
//		            objectNode.put("responseMessage", "Failed To Fetch UserProfile");
//		            return ResponseEntity.badRequest().body(objectNode);
//		        }
//		    }
//		    
//		    catch (Exception e) {
//		        objectNode.put("responseCode", "400");
//		        objectNode.put("status", "error");
//		        objectNode.put("responseMessage", e.getMessage());
//		        return ResponseEntity.badRequest().body(objectNode);
//		    }
//		}
//	
//}
//
