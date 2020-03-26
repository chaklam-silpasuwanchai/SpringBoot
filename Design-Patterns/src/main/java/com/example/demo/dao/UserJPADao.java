package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserJPADao extends JpaRepository<User, Integer> {

}
