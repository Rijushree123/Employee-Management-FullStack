package com.coffeebreak.ems_backend.dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
}
