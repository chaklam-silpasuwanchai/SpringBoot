package com.example.locking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.locking.model.Product;
import com.example.locking.repo.ProductRepo;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepo repo;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product findOne(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@PutMapping
	public Product put(@RequestBody Product product) {
		return repo.save(product);
	}
	
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return repo.save(product);
	}
}
