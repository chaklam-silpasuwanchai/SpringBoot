package com.example.hibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.hibernate.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
}
