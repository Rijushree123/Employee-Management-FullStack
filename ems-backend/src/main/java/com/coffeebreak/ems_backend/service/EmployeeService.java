package com.coffeebreak.ems_backend.service;

import java.util.List;

import com.coffeebreak.ems_backend.dto.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto creatEmployee(EmployeeDto employeeDto);

	public EmployeeDto getEmployeeById(Long employeeId);

	public List<EmployeeDto> getAllEmployee();

	public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee);

	public void deleteEmployee(Long empId);
}
