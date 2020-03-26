package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
	//by default, CrudeRepository already supports basic query such as save or findbyId
	
	//also supports many auto query by fields
	List<User> findByNationality(String nationality); //by default, Spring will support findBy<Attribute>
	List<User> findByUidGreaterThan(int uid);
	
	//also supports writing your own query
	@Query("from User where nationality = ?1 order by name ")
	List<User> findByNationalitySorted(String nationality);
}
