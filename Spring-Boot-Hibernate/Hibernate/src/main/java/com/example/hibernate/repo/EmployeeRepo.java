package com.example.hibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibernate.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
}
