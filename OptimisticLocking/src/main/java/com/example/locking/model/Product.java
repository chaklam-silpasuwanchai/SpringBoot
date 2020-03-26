package com.example.locking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	The @Version annotation assumes a column in the 
//	database exists to represent the field that the 
//	annotation is tagged to. That’s really it. 
//	JPA takes care of incrementing the version as well 
//	as all the version checking upon save.
//  We MUST not manually update or increment version by ourselves
	
	@Version
	private Long version;
	
	private String name;
	
	private Long price;

}
