package com.example.ml.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.ml.model.User;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserJPADao extends JpaRepository<User, Integer> {

	//find all users that subscribe to list with lid
	@Query(value = "SELECT * FROM user JOIN subscribing_list ON user.id = subscribing_list.uid WHERE subscribing_list.lid = :lid", nativeQuery=true)
	List<User> findUsersByList(int lid);
	
	User findByUsername(String username);
	
}
