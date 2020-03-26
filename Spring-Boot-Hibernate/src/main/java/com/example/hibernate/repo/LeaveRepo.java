package com.example.hibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibernate.model.Leave;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {
	
}
