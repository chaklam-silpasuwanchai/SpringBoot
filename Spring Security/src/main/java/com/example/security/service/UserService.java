package com.example.security.service;

import com.example.security.model.User;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
}
