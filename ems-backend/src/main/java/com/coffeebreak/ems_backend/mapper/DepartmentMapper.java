package com.coffeebreak.ems_backend.mapper;

import com.coffeebreak.ems_backend.dto.DepartmentDto;
import com.coffeebreak.ems_backend.entity.Department;

public class DepartmentMapper {

	public static DepartmentDto mapToDepartmentDto(Department department) {
		return new DepartmentDto(department.getId(), department.getDepartmentName(),
				department.getDepartmentDescription());
	}

	public static Department mapToDepartment(DepartmentDto departmentDto) {
		return new Department(departmentDto.getId(), departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription());
	}

}
