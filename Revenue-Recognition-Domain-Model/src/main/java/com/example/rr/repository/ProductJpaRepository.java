package com.example.rr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rr.model.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

}
