package com.coffeebreak.ems_backend.mapper;

import com.coffeebreak.ems_backend.dto.EmployeeDto;
import com.coffeebreak.ems_backend.entity.Employee;

public class EmployeeMapper {
	// Employee JPA Entity into Employee DTO
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(),
				employee.getDepartment().getId());
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		return employee;
	}

}
