package com.coffeebreak.ems_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Student {
	@Id
	private int id;
	private String name;
	private String email;
}
