package com.coffeebreak.ems_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coffeebreak.ems_backend.dto.EmployeeDto;
import com.coffeebreak.ems_backend.entity.Department;
import com.coffeebreak.ems_backend.entity.Employee;
import com.coffeebreak.ems_backend.exception.ResourceNotFoundException;
import com.coffeebreak.ems_backend.mapper.EmployeeMapper;
import com.coffeebreak.ems_backend.repository.DepartmentRepository;
import com.coffeebreak.ems_backend.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;

	@Override
	public EmployeeDto creatEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Department department = departmentRepository.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department does not exists with id: " + employeeDto.getDepartmentId()));
		employee.setDepartment(department);
		Employee savEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee is non existent with id: " + id));
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department does not exists with id: " + updatedEmployee.getDepartmentId()));
		employee.setDepartment(department);
		employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public void deleteEmployee(Long empId) {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee is non existent with id: " + empId));
		employeeRepository.deleteById(empId);
	}

}
