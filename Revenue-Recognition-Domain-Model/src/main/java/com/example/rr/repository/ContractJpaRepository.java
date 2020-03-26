package com.example.rr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rr.model.Contract;

public interface ContractJpaRepository extends JpaRepository<Contract, Integer> {

}
