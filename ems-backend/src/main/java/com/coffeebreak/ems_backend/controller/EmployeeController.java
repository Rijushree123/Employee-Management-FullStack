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

import com.coffeebreak.ems_backend.dto.EmployeeDto;
import com.coffeebreak.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

	private EmployeeService employeeService;

	// Add Employee Rest API
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.creatEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	// Get Employee Rest API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
		EmployeeDto savedEmployee = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
	}

	// Get Employee Rest API
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployee();
		return ResponseEntity.ok(employees);
	}

	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
			@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long empId) {
		employeeService.deleteEmployee(empId);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}
}
