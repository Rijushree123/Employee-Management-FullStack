package com.coffeebreak.ems_backend.controller;

import java.util.List;

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

import com.coffeebreak.ems_backend.dto.DepartmentDto;
import com.coffeebreak.ems_backend.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
@CrossOrigin("*")
public class DepartmentController {

	private DepartmentService departmentService;

	// Add Employee Rest API
	@PostMapping
	public ResponseEntity<DepartmentDto> createEmployee(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepartment = departmentService.creatDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}

	// Get Employee Rest API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
		DepartmentDto savedDepartment = departmentService.getDepartmentById(departmentId);
		return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
	}

	// Get Employee Rest API
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		List<DepartmentDto> departments = departmentService.getAllDepartment();
		return ResponseEntity.ok(departments);
	}

	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,
			@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepartment = departmentService.updateDepartment(departmentId, departmentDto);
		return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
	}

	@DeleteMapping("{departmentId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long departmentId) {
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department Deleted Successfully");
	}
}
