package com.coffeebreak.ems_backend.mapper;

import com.coffeebreak.ems_backend.dto.EmployeeDto;
import com.coffeebreak.ems_backend.entity.Employee;

public class EmployeeMapper {
	//Employee JPA Entity into Employee DTO
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail()
		);
	}
	
	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail()
		);
	}

}
