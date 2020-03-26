package com.example.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernate.model.Employee;
import com.example.hibernate.repo.EmployeeRepo;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {    	
    	Employee emp = empRepo.findById(id).orElse(null);
        return emp;
       
   }

}
