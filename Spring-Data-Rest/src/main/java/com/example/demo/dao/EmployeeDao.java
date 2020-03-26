package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Employee;


@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
//path is the URL access path
//collectionResourceRel is the collection name of the resource
//mostly commonly, path and collectionResourceRel will be same, here i just make an example for you to understand
//there is also one more which is itemResourceRel which is for single item
public interface EmployeeDao extends JpaRepository<Employee, Integer> 
{

}
