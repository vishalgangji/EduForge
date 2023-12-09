package com.example.eduForge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eduForge.dtos.CourseDto;
import com.example.eduForge.entity.CourseEntity;
import com.example.eduForge.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class CourseController {

	@Autowired
	ObjectMapper mapper;
	@Autowired
	CourseService courseService;

	@PostMapping("/AddCourse")

	public ResponseEntity<ObjectNode> AddCourse(@RequestBody CourseDto CourseDto) {
		ObjectNode objectNode = mapper.createObjectNode();

		try {
			
			CourseEntity Course=courseService.insertCourse(CourseDto);
			if (Course!=null) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "Course Created Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				throw new IllegalArgumentException("error ");
			}
		} catch (IllegalArgumentException e) {
			objectNode.put("responseCode", "400");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Add Course " + " " + e.getMessage());
			return ResponseEntity.badRequest().body(objectNode);
		} catch (Exception e) {
			e.printStackTrace();
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Add Course" + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

	@PutMapping("/updateCourse")
	public ResponseEntity<ObjectNode> updateCourse(@RequestBody CourseDto CourseDto) {

		ObjectNode objectNode = mapper.createObjectNode();

		try {

			boolean isUpdated = courseService.updateCourse(CourseDto);

			if (isUpdated) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "course updated Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Update course");
				return ResponseEntity.badRequest().body(objectNode);
			}
		}

		catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Update course " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<ObjectNode> deleteInvoice(@PathVariable Long id) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			boolean invoice = courseService.deleteInvoice(id);

			if (invoice) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.put("responseMessage", "course Deleted Successfully");
				return ResponseEntity.ok().body(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Delete course");
				return ResponseEntity.badRequest().body(objectNode);
			}
		} catch (IllegalArgumentException e) {
			objectNode.put("responseCode", "400");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Delete course " + " " + e.getMessage());
			return ResponseEntity.badRequest().body(objectNode);
		} catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Delete course");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

//    @RequestBody Map<String, String> requestBody,
	@GetMapping("/getAllCourse")
	public ResponseEntity<ObjectNode> getAllCourse() {
		ObjectNode objectNode = mapper.createObjectNode();

		try {

			
			List<CourseEntity> courseEntitys = courseService.getAllCourse();

			if (!courseEntitys.isEmpty()) {
				objectNode.put("responseCode", "200");
				objectNode.put("status", "success");
				objectNode.putPOJO("responseMessage", courseEntitys);
				return ResponseEntity.ok(objectNode);
			} else {
				objectNode.put("responseCode", "400");
				objectNode.put("status", "error");
				objectNode.put("responseMessage", "Failed To Fetch Course");
				return ResponseEntity.badRequest().body(objectNode);
			}
		} catch (Exception e) {
			objectNode.put("responseCode", "500");
			objectNode.put("status", "error");
			objectNode.put("responseMessage", "Failed To Fetch Course " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);

		}
	}

////    @RequestBody Map<String, String> requestBody,
//	@PostMapping("/fetchInvoiceNumber/{invoiceNumber}")
//	public ResponseEntity<ObjectNode> fetchInvoiceNumber(@RequestBody Map<String, String> requestBody,
//			@PathVariable String invoiceNumber, HttpSession session) {
//		ObjectNode objectNode = mapper.createObjectNode();
//
//		try {
//
//			// String uniqueId="SITD9893";
//			String uniqueId = requestBody.get("uniqueId");
//			if (uniqueId == null) {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "You Are Not Validated");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//
//			// Use the "uniqueId" value to fetch invoices
//			List<InvoiceEntity> decryptedInvoices = invoiceService.fetchInvoiceNumber(invoiceNumber);
//
//			if (!decryptedInvoices.isEmpty()) {
//				objectNode.put("responseCode", "200");
//				objectNode.put("status", "success");
//				objectNode.putPOJO("responseMessage", decryptedInvoices);
//				return ResponseEntity.ok(objectNode);
//			} else {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "Failed To Fetch Invoice - no invoice found");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//		} catch (Exception e) {
//			objectNode.put("responseCode", "500");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice " + e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);
//
//		}
//	}
//
//	@PostMapping("/fetchInvoiceById/{id}")
//	public ResponseEntity<ObjectNode> fetchInvoiceById(@PathVariable Long id) {
//		ObjectNode objectNode = mapper.createObjectNode();
//		try {
//			InvoiceEntity invoice = invoiceService.fetchInvoiceById(id);
//			if (invoice != null) {
//				objectNode.put("responseCode", "200");
//				objectNode.put("status", "success");
//				objectNode.putPOJO("responseMessage", invoice);
//				return ResponseEntity.ok().body(objectNode);
//			} else {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "Failed To Fetch Invoice");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//		} catch (IllegalArgumentException e) {
//			objectNode.put("responseCode", "400");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice" + " " + e.getMessage());
//			return ResponseEntity.badRequest().body(objectNode);
//		} catch (Exception e) {
//			objectNode.put("responseCode", "500");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);
//
//		}
//	}
//
//	@PostMapping("/fetchOriginalInvoiceNumber")
//	public ResponseEntity<ObjectNode> fetchOriginalInvoiceNumber(@RequestBody Map<String, String> requestBody) {
//		String uniqueId = requestBody.get("uniqueId");
//		String customerId = requestBody.get("customerId");
////	String customerId="Customer ID Example";
////	String uniqueId="SITD9893";
//
//		ObjectNode objectNode = mapper.createObjectNode();
//		try {
//			List<InvoiceEntity> invoice = invoiceService.fetchOriginalInvoiceNumber(uniqueId, customerId);
//			if (!invoice.isEmpty()) {
//				objectNode.put("responseCode", "200");
//				objectNode.put("status", "success");
//				objectNode.putPOJO("responseMessage", invoice);
//				return ResponseEntity.ok().body(objectNode);
//			} else {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "Failed To Fetch Invoice - no Invoice Found");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//		} catch (IllegalArgumentException e) {
//			objectNode.put("responseCode", "400");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice" + " " + e.getMessage());
//			return ResponseEntity.badRequest().body(objectNode);
//		} catch (Exception e) {
//			objectNode.put("responseCode", "500");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);
//
//		}
//
//	}
//
//	@PostMapping("/fetchOriginalInvoiceDate")
//	public ResponseEntity<ObjectNode> fetchOriginalInvoiceDate(@RequestBody Map<String, String> requestBody) {
//		String uniqueId = requestBody.get("uniqueId");
//		String invoiceNumber = requestBody.get("invoiceNumber");
////	String invoiceNumber="inv-00004";
////	String uniqueId="SITD9893";
//
//		ObjectNode objectNode = mapper.createObjectNode();
//		try {
//			List<InvoiceEntity> invoice = invoiceService.fetchOriginalInvoiceDate(uniqueId, invoiceNumber);
//			if (invoice.size() > 0) {
//				objectNode.put("responseCode", "200");
//				objectNode.put("status", "success");
//				objectNode.putPOJO("responseMessage", invoice);
//				return ResponseEntity.ok().body(objectNode);
//			} else {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "Failed To Fetch Invoice");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//		} catch (IllegalArgumentException e) {
//			objectNode.put("responseCode", "400");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice" + " " + e.getMessage());
//			return ResponseEntity.badRequest().body(objectNode);
//		} catch (Exception e) {
//			objectNode.put("responseCode", "500");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Fetch Invoice " + e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);
//
//		}
//	}
//
//	@DeleteMapping("/deleteByInvoiceNumber/{invoiceNumber}")
//	public ResponseEntity<ObjectNode> deleteByBillNumber(@PathVariable String invoiceNumber) {
//		ObjectNode objectNode = mapper.createObjectNode();
//		try {
//			boolean invoice = invoiceService.deleteInvoice(invoiceNumber);
//			if (invoice == true) {
//				objectNode.put("responseCode", "200");
//				objectNode.put("status", "success");
//				objectNode.put("responseMessage", "Invoice Deleted Successfully");
//				return ResponseEntity.ok().body(objectNode);
//			} else {
//				objectNode.put("responseCode", "400");
//				objectNode.put("status", "error");
//				objectNode.put("responseMessage", "Failed To Delete Invoice");
//				return ResponseEntity.badRequest().body(objectNode);
//			}
//		} catch (IllegalArgumentException e) {
//			objectNode.put("responseCode", "400");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Delete Invoice" + " " + e.getMessage());
//			return ResponseEntity.badRequest().body(objectNode);
//		} catch (Exception e) {
//			objectNode.put("responseCode", "500");
//			objectNode.put("status", "error");
//			objectNode.put("responseMessage", "Failed To Delete Invoice");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode);
//
//		}
//	}

}