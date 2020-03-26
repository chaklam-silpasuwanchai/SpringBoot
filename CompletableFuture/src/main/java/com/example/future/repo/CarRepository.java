package com.example.future.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.future.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
