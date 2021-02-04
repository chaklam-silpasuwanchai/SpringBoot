package com.example.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Role;

public interface RoleJPADao extends JpaRepository<Role, Integer>{

}
