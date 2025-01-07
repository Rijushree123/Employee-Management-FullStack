package com.coffeebreak.ems_backend.service;

import java.util.List;

import com.coffeebreak.ems_backend.dto.DepartmentDto;

public interface DepartmentService {

	public DepartmentDto creatDepartment(DepartmentDto departmentDto);

	public DepartmentDto getDepartmentById(Long departmentId);

	public List<DepartmentDto> getAllDepartment();

	public DepartmentDto updateDepartment(Long id, DepartmentDto updatedDepartmentDto);

	public void deleteDepartment(Long departmentId);
}
