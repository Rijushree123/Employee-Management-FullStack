package com.coffeebreak.ems_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coffeebreak.ems_backend.dto.DepartmentDto;
import com.coffeebreak.ems_backend.entity.Department;
import com.coffeebreak.ems_backend.exception.ResourceNotFoundException;
import com.coffeebreak.ems_backend.mapper.DepartmentMapper;
import com.coffeebreak.ems_backend.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto creatDepartment(DepartmentDto departmentDto) {
		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department savEmployee = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savEmployee);
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream().map((dep) -> DepartmentMapper.mapToDepartmentDto(dep)).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long id, DepartmentDto updatedDepartmentDto) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department is non existent with id: " + id));
		department.setDepartmentName(updatedDepartmentDto.getDepartmentName());
		department.setDepartmentDescription(updatedDepartmentDto.getDepartmentDescription());
		departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is non existent with id: " + departmentId));
		departmentRepository.deleteById(departmentId);

	}

}
