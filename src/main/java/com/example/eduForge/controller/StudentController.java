package com.example.eduForge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eduForge.dtos.StudentDto;
import com.example.eduForge.dtos.StudentDto;
import com.example.eduForge.entity.StudentEntity;
import com.example.eduForge.entity.StudentEntity;
import com.example.eduForge.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/api")
@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	ObjectMapper mapper;

	@PostMapping("/insertStudent")


	public ResponseEntity<ObjectNode> AddStudent(@RequestBody StudentDto StudentDto) {
		ObjectNode objectNode = mapper.createObjectNode();

		try {

			StudentEntity Student = studentService.insertStudent(StudentDto);
			if (Student != null) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "Student Created Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				throw new IllegalArgumentException("error ");
			}
		} catch (IllegalArgumentException e) {
			objectNode.put("responseCode", "400");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Add Student " + " " + e.getMessage());
			return ResponseEntity.badRequest().body(objectNode);
		} catch (Exception e) {
			e.printStackTrace();
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Add Student" + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

	@PutMapping("/updateStudent")
	public ResponseEntity<ObjectNode> updateStudent(@RequestBody StudentDto StudentDto) {

		ObjectNode objectNode = mapper.createObjectNode();

		try {

			boolean isUpdated = studentService.updateStudent(StudentDto);

			if (isUpdated) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "Student updated Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Update Student");
				return ResponseEntity.badRequest().body(objectNode);
			}
		}

		catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Update Student " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<ObjectNode> deleteInvoice(@PathVariable Long id) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			boolean invoice = studentService.deleteInvoice(id);

			if (invoice) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "Student Deleted Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "Student Deleted Successfully");
				return ResponseEntity.ok().body(objectNode);
			}
		} catch (IllegalArgumentException e) {
			objectNode.put("responseCode", "400");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Delete Student " + " " + e.getMessage());
			return ResponseEntity.badRequest().body(objectNode);
		} catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Delete Student");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

//@RequestBody Map<String, String> requestBody,
	@GetMapping("/getAllStudent")
	public ResponseEntity<ObjectNode> getAllStudent() {
		ObjectNode objectNode = mapper.createObjectNode();

		try {

			List<StudentEntity> StudentEntitys = studentService.getAllStudent();

			if (!StudentEntitys.isEmpty()) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.putPOJO("responseMessage", StudentEntitys);
				return ResponseEntity.ok(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Fetch Student");
				return ResponseEntity.badRequest().body(objectNode);
			}
		} catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Fetch Student " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<ObjectNode> getStudent(@PathVariable Long id) {
		ObjectNode objectNode = mapper.createObjectNode();

		try {

			StudentEntity StudentEntitys = studentService.getStudent(id);

			if (StudentEntitys!=null) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.putPOJO("responseMessage", StudentEntitys);
				return ResponseEntity.ok(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Fetch Student");
				return ResponseEntity.badRequest().body(objectNode);
			}
		} catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Fetch Student " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}
}
