package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.RoleFactory;

@RestController
@RequestMapping("/")
public class FactoryController {
	private RoleFactory factory;

	@Autowired
	public FactoryController(RoleFactory factory) {
		this.factory = factory;
	}

	@PostMapping("addRole/{type}/{name}")
	public Role addAnimal(@PathVariable String type, @PathVariable String name) {
		Role role = this.factory.createRole(type);
		role.setName(name);
		role.checkAccess();
		return role;
	}
}
