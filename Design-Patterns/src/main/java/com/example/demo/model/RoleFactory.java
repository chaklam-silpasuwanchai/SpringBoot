package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
	
	public Role createRole(String role) {
		switch(role) {
		case "admin":
			return new Admin();
		case "member":
			return new Member();
		default:
			throw new UnsupportedOperationException("Unsupported role");
		}
		
	}
	
}
