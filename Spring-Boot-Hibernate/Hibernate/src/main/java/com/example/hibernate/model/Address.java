package com.example.hibernate.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE) //Provide cache strategy.
public class Address{
	
	@EmbeddedId
	private AddressId id;
	
	
	//Default JPA
	/*
	   OneToMany: LAZY
	   ManyToMany: LAZY
	   ManyToOne: EAGER
       OneToOne: EAGER
	 */
	
	//When you load address, it will not load emp by default
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee emp;
}
